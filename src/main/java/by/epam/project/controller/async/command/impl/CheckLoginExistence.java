package by.epam.project.controller.async.command.impl;

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

import static by.epam.project.controller.parameter.RequestParameterKey.*;

public class CheckLoginExistence implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

        String login = (String) requestParameters.get(USER_LOGIN);

        if (!UserValidator.isLoginCorrect(login)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            Optional<User> user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
