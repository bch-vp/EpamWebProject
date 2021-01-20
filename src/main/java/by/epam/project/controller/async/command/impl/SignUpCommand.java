package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.constant.PagePath;
import by.epam.project.controller.constant.PropertieKey;
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

import static by.epam.project.controller.constant.ParameterKey.*;

public class SignUpCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String resultJson = null;

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
            String content  = request.getContentType();

            String login = (String) requestParameters.get(USER_LOGIN);
            String password = (String) requestParameters.get(USER_PASSWORD);
            String email = (String) requestParameters.get(USER_EMAIL);
            String firstName = (String) requestParameters.get(USER_NAME);
            String lastName = (String) requestParameters.get(USER_SURNAME);
            String phone = (String) requestParameters.get(USER_PHONE);

            Map<String, String> requestData = userService.defineSignUpData(login,
                    password, email, firstName, lastName, phone);

            if (UserValidator.defineIncorrectValues(requestData)) {
                User newUser = new User(login, firstName, lastName, phone, email);
                userService.signUpUser(newUser, password);

                String locale = (String) session.getAttribute(LANGUAGE);

                String emailSubjectWithLocale = ContentUtil.getWithLocale(locale, PropertieKey.EMAIL_SUBJECT);
                String emailBodyWithLocale = ContentUtil.getWithLocale(locale, PropertieKey.EMAIL_BODY);

                emailService.sendActivationEmail(newUser, emailSubjectWithLocale,
                        emailBodyWithLocale, PagePath.EMAIL_ACTIVATION_LINK);

                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                JsonNode jsonTree = JsonUtil.addObjectToJsonTree(null, "error");
                String language = (String) session.getAttribute(LANGUAGE);

                if (requestData.get(LOGIN_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, "login_not_unique", error, "error");
                }
                if (requestData.get(PHONE_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language,
                            PropertieKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, "telephone_number_not_unique", error, "error");
                }
                if (requestData.get(EMAIL_UNIQUE).equals(NOT_UNIQUE)) {
                    String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                    JsonUtil.addNodeToJsonTree(jsonTree, "email_not_unique", error, "error");
                }

                resultJson = JsonUtil.jsonTreeToJson(jsonTree);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            if(resultJson != null && !resultJson.isEmpty()) {
                response.setContentType(CONTENT_TYPE);
                response.setCharacterEncoding(ENCODING);
                response.getWriter().write(resultJson);
            }
        } catch (ServiceException | IOException exp) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}