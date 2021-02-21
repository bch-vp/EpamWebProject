package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.project.controller.parameter.ContentKey.ERROR_PROFILE_OLD_PASSWORD_NOT_EQUAL_LOGIN_PASSWORD;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.*;

public class ChangePasswordByOldPassCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String language = (String) session.getAttribute(LANGUAGE);

        User user = (User) session.getAttribute(USER);
        String login = user.getLogin();

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String oldPassword = (String) requestParameters.get(OLD_PASSWORD);
            String newPassword = (String) requestParameters.get(NEW_PASSWORD);

            if (ServiceValidator.isPasswordCorrect(oldPassword)
                    && ServiceValidator.isPasswordCorrect(newPassword)
                    && oldPassword.equals(newPassword)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            boolean isEqual = userService.isPasswordEqualLoginPassword(login, oldPassword);
            if (isEqual) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_OLD_PASSWORD_NOT_EQUAL_LOGIN_PASSWORD, language);
                return;
            }
            userService.updatePasswordByLogin(login, newPassword);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
