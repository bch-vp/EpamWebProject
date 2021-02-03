package by.epam.project.controller.async.command.impl.guest;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.controller.parameter.PropertieKey;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.EmailService;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static by.epam.project.controller.parameter.RequestParameterKey.*;
import static by.epam.project.controller.parameter.SessionAttributeKey.TIME_CREATED;
import static by.epam.project.controller.parameter.SessionAttributeKey.UNIQUE_KEY;

public class ChangePasswordByEmail implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailService emailService = EmailServiceImpl.getInstance();
    private static final int DIFF_RANGE = 900_000;
    private static final int MIN_RANGE = 100_000;
    private static final int TIMER_SEC = 300;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(LANGUAGE);
        String sessionUniqueKey = (String) session.getAttribute(UNIQUE_KEY);

        String login = (String) requestParameters.get(USER_LOGIN);
        String email = (String) requestParameters.get(USER_EMAIL);
        String newPassword = (String) requestParameters.get(USER_NEW_PASSWORD);
        String requestUniqueKey = (String) requestParameters.get(UNIQUE_KEY);

        // if data not correct
        if (!UserValidator.isLoginCorrect(login)
                || !UserValidator.isEmailCorrect(email)
                || !UserValidator.isPasswordCorrect(newPassword)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Optional<User> userOptional = userService.findUserByLogin(login);

            // if user by login not found
            if (userOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // if email not equal with user's email
            User user = userOptional.get();
            if (!user.getEmail().equals(email)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorEmailIncorrect = ContentUtil.getWithLocale(locale,
                        PropertieKey.ERROR_CHANGING_PASSWORD_EMAIL_INCORRECT);

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put(ErrorKey.ERROR, errorEmailIncorrect);

                String json = JsonUtil.toJson(responseMap);
                writeJsonToResponse(response, json);
                return;
            }

            // if session not contain uniqueKey, that's why need to send new CODE to email
            if (sessionUniqueKey == null || sessionUniqueKey.isEmpty()) {
                int key = MIN_RANGE + new Random().nextInt(DIFF_RANGE);
                String uniqueKey = String.valueOf(key);

                session.setAttribute(TIME_CREATED, System.currentTimeMillis());
                session.setAttribute(UNIQUE_KEY, uniqueKey);

                String emailSubjectWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.EMAIL_SUBJECT_GUEST_CHANGING_PASSWORD);
                String emailBodyWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.EMAIL_BODY_GUEST_CHANGING_PASSWORD);

                emailService.sendConfirmationChangingPassword(user, emailSubjectWithLocale,
                        emailBodyWithLocale, uniqueKey);

                String emailNotificationWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.INFO_CHANGING_PASSWORD_EMAIL_CONFIRMATION);

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put(ErrorKey.ERROR, emailNotificationWithLocale);

                String json = JsonUtil.toJson(responseMap);
                writeJsonToResponse(response, json);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // if uniqueKey from request not equal session uniqueKey
            if (!requestUniqueKey.equals(sessionUniqueKey)) {
                String emailNotificationWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.ERROR_CHANGING_PASSWORD_UNIQUE_KEY_INCORRECT);

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put(ErrorKey.ERROR, emailNotificationWithLocale);

                String json = JsonUtil.toJson(responseMap);
                writeJsonToResponse(response, json);

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long timeCreated =(long) session.getAttribute(TIME_CREATED);
            long timeNow = System.currentTimeMillis();
            long diff = (timeNow - timeCreated)/1000;
            boolean isTimeNotExpired = diff <= TIMER_SEC;
            if (isTimeNotExpired) {
                userService.updatePasswordByLogin(login, newPassword);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                String errorTimeExpired = ContentUtil.getWithLocale(locale,
                        PropertieKey.ERROR_CHANGING_PASSWORD_GUEST_TIME_EXPIRED);

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put(ErrorKey.ERROR, errorTimeExpired);

                String json = JsonUtil.toJson(responseMap);
                writeJsonToResponse(response, json);

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            session.removeAttribute(UNIQUE_KEY);
        } catch (ServiceException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void writeJsonToResponse(HttpServletResponse response, String json) throws IOException {
        if (json != null && !json.isEmpty()) {
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().write(json);
        }
    }
}
