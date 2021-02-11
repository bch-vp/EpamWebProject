package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.parameter.PropertieKey;
import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.*;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.URLUtil;
import by.epam.project.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class SignInSyncCommand implements SyncCommand {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String language = (String) session.getAttribute(LANGUAGE);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
                Optional<User> userOptional = userService.signInUser(login, password);
                if (userOptional.isPresent()) {
                    router.setRedirect();

                    User user = userOptional.get();
                    session.setAttribute(USER, user);

                    //write equal pass

                    User.Role role = user.getRole();

                    String redirectURL;
                    if (role == User.Role.CLIENT) {
                        session.setAttribute(CURRENT_PAGE, PASSING_BY_CLIENT);
                        redirectURL = URLUtil.createRedirectURL(request,
                                CommandType.PASSING_BY_CLIENT.toString().toLowerCase());
                    } else {
                        session.setAttribute(CURRENT_PAGE, PASSING_BY_ADMIN);
                        redirectURL = URLUtil.createRedirectURL(request,
                                CommandType.PASSING_BY_ADMIN.toString().toLowerCase());
                    }

                    router.setCurrentPage(redirectURL);
                    return router;

                } else {
                    String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_IN_NOT_FOUND);
                    session.setAttribute(ErrorKey.LOGIN_NOT_FOUND, error);

                    router.setRedirect();
                    String redirectUrl = URLUtil.createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                    router.setCurrentPage(redirectUrl);
                }
            }
        } catch (ServiceException exp) {
//            String propertieKey = exp.getCause().getMessage();// ??????????
//            String error = ContentUtil.getWithLocale(language, propertieKey);
//            session.setAttribute(ErrorKey.DATABASE_CONNECTION_NOT_RECEIVED, error);

            router.setRedirect();
            String redirectUrl = URLUtil.createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
            router.setCurrentPage(redirectUrl);
        }
        return router;
    }
}
