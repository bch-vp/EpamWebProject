package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.constant.AttributeKey;
import by.epam.project.controller.constant.PagePath;
import by.epam.project.controller.constant.PropertieKey;
import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.*;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.project.controller.constant.ParameterKey.LANGUAGE;

public class SignInCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            Optional<User> currentUser = userService.signInUser(login, password);
            if (currentUser.isPresent()) {
                router.setCurrentPage(PagePath.CLIENT);
            } else {
                String language = (String) session.getAttribute(LANGUAGE);
                String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_IN_NOT_FOUND);
                session.setAttribute(AttributeKey.ERROR_SIGN_IN_NOT_FOUND, error);

                router.setRedirect();
                String redirectUrl = createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                router.setCurrentPage(redirectUrl);
            }
        } catch (ServiceException exp) {
            router.setCurrentPage(PagePath.ERROR_500);
        }
        return router;
    }
}
