package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.controller.parameter.PropertieKey;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.UserValidator;
import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class SignUpCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        String responseJson = null;

        Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

        String login = (String) requestParameters.get(LOGIN);
        String password = (String) requestParameters.get(PASSWORD);
        String firstName = (String) requestParameters.get(FIRST_NAME);
        String lastName = (String) requestParameters.get(LAST_NAME);
        String phone = (String) requestParameters.get(TELEPHONE_NUMBER);
        String email = (String) requestParameters.get(EMAIL);

        try {
            Map<String, String> requestData = userService.defineSignUpData(login,
                    password, email, firstName, lastName, phone);

            if (UserValidator.defineIncorrectValues(requestData)) {
                User newUser = new User(login, firstName, lastName, phone, email,
                        User.Role.CLIENT.getRoleId(), false);
                userService.signUpUser(newUser, password);

                String locale = (String) session.getAttribute(LANGUAGE);

                String emailSubjectWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.EMAIL_SUBJECT_ACTIVATION_SIGN_UP);
                String emailBodyWithLocale = ContentUtil.getWithLocale(locale,
                        PropertieKey.EMAIL_BODY_ACTIVATION_SIGN_UP);

                emailService.sendActivationEmail(newUser, emailSubjectWithLocale,
                        emailBodyWithLocale, PagePath.EMAIL_ACTIVATION_LINK);

                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                JsonNode jsonTree = JsonUtil.addObjectToJsonTree(null, ErrorKey.ERROR);

                if (requestData.get(LOGIN_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.LOGIN_NOT_UNIQUE, error, ErrorKey.ERROR);
                }
                if (requestData.get(TELEPHONE_NUMBER_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language,
                            PropertieKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.TELEPHONE_NUMBER_NOT_UNIQUE, error, ErrorKey.ERROR);
                }
                if (requestData.get(EMAIL_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.EMAIL_NOT_UNIQUE, error, ErrorKey.ERROR);
                }

                responseJson = JsonUtil.jsonTreeToJson(jsonTree);
            }
        } catch (ServiceException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        if (responseJson != null && !responseJson.isEmpty()) {
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().write(responseJson);
        }
    }
}