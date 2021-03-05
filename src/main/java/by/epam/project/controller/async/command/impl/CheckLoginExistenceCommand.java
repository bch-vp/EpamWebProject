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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.LOGIN;

public class CheckLoginExistenceCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String login = (String) requestParameters.get(LOGIN);

            if (!ServiceValidator.isLoginCorrect(login)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            Optional<User> user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | IOException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
