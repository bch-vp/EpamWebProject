package by.epam.project.controller.async.command.impl.client;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ChangePasswordByOldPass implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

        String login = (String) requestParameters.get(LOGIN);
        String oldPassword = (String) requestParameters.get(OLD_PASSWORD);
        String newPassword = (String) requestParameters.get(NEW_PASSWORD);

        try {
            if (UserValidator.isLoginCorrect(login)
                    && UserValidator.isPasswordCorrect(oldPassword)
                    && UserValidator.isPasswordCorrect(newPassword)) {
                if (!oldPassword.equals(newPassword)) { // if oldPass not equal with newPass
                    Optional<User> user = userService.findUserByLogin(login);
                    if (user.isPresent()) { // if found acc with that login

                        boolean isEqual = userService.isPasswordEqualLoginPassword(login, oldPassword);
                        if (isEqual) { // if oldPass equal with pass of login
                            //update pass
                            userService.updatePasswordByLogin(login, newPassword);
                            response.setStatus(HttpServletResponse.SC_OK);
                        } else {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (ServiceException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
