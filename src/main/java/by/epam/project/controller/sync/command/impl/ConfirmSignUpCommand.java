package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static by.epam.project.controller.parameter.PagePath.ERROR_500;
import static by.epam.project.controller.parameter.PagePath.NOTIFICATION_SUCCESS;
import static by.epam.project.controller.parameter.ParameterKey.LOGIN;
import static by.epam.project.controller.parameter.ParameterKey.SHOPPING_CART;

public class ConfirmSignUpCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    private static final boolean ACTIVATED = true;

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router(NOTIFICATION_SUCCESS);

        String login = request.getParameter(LOGIN);

        session.setAttribute(SHOPPING_CART, new ArrayList<String>());

        try {
            userService.updateActivationStatusByLogin(login, User.Status.ACTIVATED);
        } catch (ServiceException exp) {
            router.setCurrentPage(ERROR_500);
            logger.error(exp);
        }

        return router;
    }
}
