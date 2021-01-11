package by.epam.project.controller.command.impl;

import by.epam.project.controller.Router;
import by.epam.project.controller.command.Command;
import by.epam.project.controller.command.MessageAttribute;
import by.epam.project.controller.command.PagePath;
import by.epam.project.controller.command.PropertiesMessage;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.project.util.RequestParameterName.LANGUAGE;

public class SignInCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

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
                String error = ContentUtil.getWithLocale(language, PropertiesMessage.ERROR_SIGN_IN_NOT_FOUND);
                session.setAttribute(MessageAttribute.ERROR_SIGN_IN_NOT_FOUND, error);
                router.setCurrentPage(PagePath.GUEST);
            }
        } catch (
                ServiceException exp) {
            LOGGER.error(exp);
            router.setCurrentPage(PagePath.ERROR_500);
        }
        return router;
    }
}
