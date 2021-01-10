package by.epam.project.controller.command.impl;

import by.epam.project.controller.Router;
import by.epam.project.controller.command.Command;
import by.epam.project.controller.command.PagePath;
import by.epam.project.controller.command.PropertiesContentKey;
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

import java.util.Map;

import static by.epam.project.util.RequestParameterName.*;

public class SignUpCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

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

                String emailSubjectWithLocale = ContentUtil.getWithLocale(locale, PropertiesContentKey.EMAIL_SUBJECT);
                String emailBodyWithLocale = ContentUtil.getWithLocale(locale, PropertiesContentKey.EMAIL_BODY);

                emailService.sendActivationEmail(newUser, emailSubjectWithLocale,
                        emailBodyWithLocale, PagePath.EMAIL_ACTIVATION_LINK);

                router = new Router(PagePath.NOTIFICATION);
            } else {
                router = new Router(PagePath.ERROR_500);
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
