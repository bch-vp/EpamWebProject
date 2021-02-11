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
import java.io.IOException;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.USER;

public class LoadProfileImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        String login = user.getLogin();

        Optional<byte[]> bytesOptional;
        try {
            bytesOptional = userService.findAvatarByLogin(login);
        } catch (ServiceException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if(bytesOptional.isEmpty()){
            //error avatar not found for this login TODO
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        byte[] bytes = bytesOptional.get();

        try {
            response.getOutputStream().write(bytes);
        } catch (IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
