package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.PagePath.ERROR_500;
import static by.epam.project.controller.parameter.PagePath.NOTIFICATION_SUCCESS;
import static by.epam.project.controller.parameter.ParameterKey.LOGIN;

public class ConfirmSignUp implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    private static final boolean ACTIVATED = true;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(NOTIFICATION_SUCCESS);

        String login = request.getParameter(LOGIN);

        try {
            userService.updateActivationStatusByLogin(login, ACTIVATED);
        } catch (ServiceException exp) {
            router.setCurrentPage(ERROR_500);
            logger.error(exp);
        }

        return router;
    }
}