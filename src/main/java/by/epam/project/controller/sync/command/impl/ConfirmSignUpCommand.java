package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.PagePath.NOTIFICATION_SUCCESS;
import static by.epam.project.controller.parameter.ParameterKey.LOGIN;

public class ConfirmSignUpCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(NOTIFICATION_SUCCESS);

        String login = request.getParameter(LOGIN);

        try {
            userService.updateActivationStatusByLogin(login, User.Status.ACTIVATED);
        } catch (ServiceException exp) {
            throw new CommandException("Error during confirming sign up", exp);
        }

        return router;
    }
}
