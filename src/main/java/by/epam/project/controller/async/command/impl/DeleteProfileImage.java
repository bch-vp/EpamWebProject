package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.project.controller.parameter.ParameterKey.USER;


public class DeleteProfileImage implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        String login = user.getLogin();

        try {
            userService.removeAvatarByLogin(login);
        } catch (ServiceException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
