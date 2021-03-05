package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ContentKey.ERROR_SIGN_IN_BANNED;
import static by.epam.project.controller.parameter.ContentKey.ERROR_SIGN_IN_NOT_ACTIVATED;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.*;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String login = (String) requestParameters.get(LOGIN);
            String password = (String) requestParameters.get(PASSWORD);

            if (!ServiceValidator.isLoginCorrect(login)
                    || !ServiceValidator.isPasswordCorrect(password)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Optional<User> userOptional = userService.signInUser(login, password);
            if (userOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            User user = userOptional.get();
            if (user.getStatus().equals(User.Status.BANNED)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_SIGN_IN_BANNED, language);
                return;
            }


            if (!user.getStatus().equals(User.Status.ACTIVATED)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_SIGN_IN_NOT_ACTIVATED, language);
                return;
            }

            session.setAttribute(USER, user);
            session.setAttribute(SHOPPING_CART, new ArrayList<Product>());

            User.Role role = user.getRole();
            if (role == User.Role.CLIENT) {
                JsonUtil.writeJsonToResponse(response, INFO, PASSING_BY_CLIENT);
            } else {
                JsonUtil.writeJsonToResponse(response, INFO, PASSING_BY_ADMIN);
            }
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
