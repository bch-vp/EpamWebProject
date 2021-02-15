package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.controller.parameter.ContentKey;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.controller.async.command.impl.util.JsonUtil;
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

import static by.epam.project.controller.parameter.ParameterKey.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String login = (String) requestParameters.get(LOGIN);
            String password = (String) requestParameters.get(PASSWORD);
            String firstName = (String) requestParameters.get(FIRST_NAME);
            String lastName = (String) requestParameters.get(LAST_NAME);
            String phone = (String) requestParameters.get(TELEPHONE_NUMBER);
            String email = (String) requestParameters.get(EMAIL);

            Map<String, String> requestData = userService.defineSignUpData(login,
                    password, email, firstName, lastName, phone);

            if (!UserValidator.defineIncorrectValues(requestData)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                JsonNode jsonTree = JsonUtil.addObjectToJsonTree(null, ErrorKey.ERROR);

                if (requestData.get(LOGIN_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.LOGIN_NOT_UNIQUE, error, ErrorKey.ERROR);
                }
                if (requestData.get(TELEPHONE_NUMBER_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language,
                            ContentKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.TELEPHONE_NUMBER_NOT_UNIQUE, error, ErrorKey.ERROR);
                }
                if (requestData.get(EMAIL_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.EMAIL_NOT_UNIQUE, error, ErrorKey.ERROR);
                }

                String json = JsonUtil.jsonTreeToJson(jsonTree);
                JsonUtil.writeJsonToResponse(response, json);
            }

            User newUser = new User(login, firstName, lastName, phone, email,
                    User.Role.CLIENT.getRoleId(), false);
            userService.signUpUser(newUser, password);

            String locale = (String) session.getAttribute(LANGUAGE);

            String emailSubjectWithLocale = ContentUtil.getWithLocale(locale,
                    ContentKey.EMAIL_SUBJECT_ACTIVATION_SIGN_UP);
            String emailBodyWithLocale = ContentUtil.getWithLocale(locale,
                    ContentKey.EMAIL_BODY_ACTIVATION_SIGN_UP);

            String URL = request.getRequestURL().toString();
            String URI = request.getRequestURI();
            String linkApp =  URL.replace(URI , EMPTY_STRING);

            String command = CommandType.CONFIRM_SIGN_UP.toString().toLowerCase();

            emailService.sendActivationEmail(newUser, emailSubjectWithLocale,
                    emailBodyWithLocale, linkApp, command);

            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}