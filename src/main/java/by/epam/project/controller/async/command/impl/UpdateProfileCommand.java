package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.parameter.ContentKey;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.UserValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.project.controller.parameter.ErrorKey.*;
import static by.epam.project.controller.parameter.ParameterKey.*;

public class UpdateProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final String EMPTY_JSON_TREE_OBJECT = "{}";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String login = (String) requestParameters.get(LOGIN);
            String oldLogin = (String) requestParameters.get(OLD_LOGIN);
            String firstName = (String) requestParameters.get(FIRST_NAME);
            String lastName = (String) requestParameters.get(LAST_NAME);
            String telephoneNumber = (String) requestParameters.get(TELEPHONE_NUMBER);
            String email = (String) requestParameters.get(EMAIL);

            User user = (User) session.getAttribute(USER);
            User.Role role = user.getRole();

            Map<String, String> requestData = UserValidator.validateParameters(login, email, firstName, lastName,
                    telephoneNumber);

            if (!UserValidator.defineIncorrectValues(requestData)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            JsonNode jsonTree = JsonUtil.addObjectToJsonTree(null, ERROR);
            if (!userService.isLoginUnique(login) && !user.getLogin().equals(login)) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, LOGIN_NOT_UNIQUE, error, ERROR);
            }
            if (!userService.isTelephoneNumberUnique(telephoneNumber) &&
                    !user.getTelephoneNumber().equals(telephoneNumber)) {
                String error = ContentUtil.getWithLocale(language,
                        ContentKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.TELEPHONE_NUMBER_NOT_UNIQUE, error, ERROR);
            }
            if (!userService.isEmailUnique(email) && !user.getEmail().equals(email)) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, EMAIL_NOT_UNIQUE, error, ERROR);
            }

            if (!jsonTree.path(ERROR).isEmpty()) {
                String responseJson = JsonUtil.jsonTreeToJson(jsonTree);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(CONTENT_TYPE);
                response.setCharacterEncoding(ENCODING);
                response.getWriter().write(responseJson);
                return;
            }

            User newUser = new User(login, firstName, lastName, telephoneNumber, email, role, false);
            userService.updateUser(newUser, oldLogin);
            session.setAttribute(USER, newUser);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
