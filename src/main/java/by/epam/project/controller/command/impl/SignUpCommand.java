package by.epam.project.controller.command.impl;

import by.epam.project.controller.Router;
import by.epam.project.controller.command.*;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.epam.project.util.RequestParameterName.*;

public class SignUpCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();

        try {
            String login = request.getParameter(USER_LOGIN);
            String password = request.getParameter(USER_PASSWORD);
            String email = request.getParameter(USER_EMAIL);
            String name = request.getParameter(USER_NAME);
            String surname = request.getParameter(USER_SURNAME);
            String phone = request.getParameter(USER_PHONE);

            Map<String, String> requestData = userService.defineSignUpData(login,
                    password, email, name, surname, phone);

            if (UserValidator.defineIncorrectValues(requestData)) {
                User newUser = new User(login, name, surname, phone, email);
                userService.signUpUser(newUser, password);

                String locale = (String) session.getAttribute(LANGUAGE);

                String emailSubjectWithLocale = ContentUtil.getWithLocale(locale, PropertiesMessage.EMAIL_SUBJECT);
                String emailBodyWithLocale = ContentUtil.getWithLocale(locale, PropertiesMessage.EMAIL_BODY);

                emailService.sendActivationEmail(newUser, emailSubjectWithLocale,
                        emailBodyWithLocale, PagePath.EMAIL_ACTIVATION_LINK);

                router.setCurrentPage(PagePath.NOTIFICATION);
            } else {
                String language = (String) session.getAttribute(LANGUAGE);

                if(requestData.get(LOGIN_UNIQUE).equals(NOT_UNIQUE)){
                    String error = ContentUtil.getWithLocale(language, PropertiesMessage.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                    session.setAttribute(MessageAttribute.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE, error);
                }
                if(requestData.get(PHONE_UNIQUE).equals(NOT_UNIQUE)){
                    String error = ContentUtil.getWithLocale(language,
                            PropertiesMessage.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                    session.setAttribute(MessageAttribute.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE, error);
                }
                if(requestData.get(EMAIL_UNIQUE).equals(NOT_UNIQUE)){
                    String error = ContentUtil.getWithLocale(language, PropertiesMessage.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                    session.setAttribute(MessageAttribute.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE, error);
                }

                router.setRedirect();
                String redirectUrl = createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                router.setCurrentPage(redirectUrl);
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router.setCurrentPage(PagePath.ERROR_500);
        }
        return router;
    }
}
