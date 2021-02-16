package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.parameter.ContentKey;
import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.util.ContentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.ParameterKey.EMPTY_STRING;
import static by.epam.project.controller.parameter.ParameterKey.LANGUAGE;

public class PassingByGuestCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        User newUser = new User("login", "firstName", "lastName", "phone", "ilya.murin.work@gmail.com",
                User.Role.CLIENT.getRoleId(), false);
//        userService.signUpUser(newUser, password);

        String locale = null;

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
        return new Router(PagePath.PASSING_BY_GUEST);
    }
}