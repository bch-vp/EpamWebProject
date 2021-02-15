package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ContentKey;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.EmailService;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static by.epam.project.controller.parameter.ContentKey.*;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.*;

public class ChangePasswordByEmailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailService emailService = EmailServiceImpl.getInstance();
    private static final int DIFF_RANGE = 900_000;
    private static final int MIN_RANGE = 100_000;
    private static final int TIMER_SEC = 300;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
            HttpSession session = request.getSession();

            String language = (String) session.getAttribute(LANGUAGE);

            String login = (String) requestParameters.get(LOGIN);
            String email = (String) requestParameters.get(EMAIL);
            String newPassword = (String) requestParameters.get(NEW_PASSWORD);

            if (!UserValidator.isLoginCorrect(login)
                    || !UserValidator.isEmailCorrect(email)
                    || !UserValidator.isPasswordCorrect(newPassword)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Optional<User> userOptional = userService.findUserByLogin(login);
            User user = userOptional.get();

            if (!user.getEmail().equals(email)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_CHANGING_PASSWORD_EMAIL_INCORRECT, language);
                return;
            }

            String sessionUniqueKey = (String) session.getAttribute(UNIQUE_KEY);
            if (sessionUniqueKey == null || sessionUniqueKey.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                JsonUtil.writeJsonToResponse(response, ERROR, INFO_CHANGING_PASSWORD_EMAIL_CONFIRMATION, language);

                int key = MIN_RANGE + new Random().nextInt(DIFF_RANGE);
                String uniqueKey = String.valueOf(key);

                session.setAttribute(TIME_CREATED, System.currentTimeMillis());
                session.setAttribute(UNIQUE_KEY, uniqueKey);

                String emailSubjectWithLocale = ContentUtil.getWithLocale(language,
                        ContentKey.EMAIL_SUBJECT_GUEST_CHANGING_PASSWORD);
                String emailBodyWithLocale = ContentUtil.getWithLocale(language,
                        ContentKey.EMAIL_BODY_GUEST_CHANGING_PASSWORD);

                emailService.sendConfirmationChangingPassword(user, emailSubjectWithLocale,
                        emailBodyWithLocale, uniqueKey);
                return;
            }

            String requestUniqueKey = (String) requestParameters.get(UNIQUE_KEY);
            if (!requestUniqueKey.equals(sessionUniqueKey)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_CHANGING_PASSWORD_UNIQUE_KEY_INCORRECT, language);
                return;
            }

            long timeCreated = (long) session.getAttribute(TIME_CREATED);
            long timeNow = System.currentTimeMillis();
            long diff = (timeNow - timeCreated) / MILLISECONDS_PER_SECOND;
            boolean isTimeExpired = diff > TIMER_SEC;
            if (isTimeExpired) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_CHANGING_PASSWORD_GUEST_TIME_EXPIRED, language);
            }

            userService.updatePasswordByLogin(login, newPassword);
            response.setStatus(HttpServletResponse.SC_OK);
            session.removeAttribute(UNIQUE_KEY);

        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
