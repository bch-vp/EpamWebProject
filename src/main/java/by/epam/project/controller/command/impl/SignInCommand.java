package by.epam.project.controller.command.impl;

import by.epam.project.controller.Router;
import by.epam.project.controller.command.Command;
import by.epam.project.controller.command.PagePath;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class SignInCommand implements Command {
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            Optional<User> currentUser = userService.signInUser(login, password);
            if (currentUser.isPresent()) {
                router.setCurrentPage(PagePath.HOME_USER);
            } else {
                request.setAttribute("signInErrorMessage", "Not found");
                router.setCurrentPage(PagePath.HOME);
            }
        } catch (
                ServiceException exp) {
            LOGGER.error(exp);
            router.setCurrentPage(PagePath.ERROR_500);
        }
        return router;
    }
}
