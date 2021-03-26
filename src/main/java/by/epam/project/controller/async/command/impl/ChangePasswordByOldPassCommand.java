package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.Parameter.*;

/**
 * The type Change password by old pass command.
 */
public class ChangePasswordByOldPassCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String oldPassword = (String) requestParameters.get(OLD_PASSWORD);
            String newPassword = (String) requestParameters.get(NEW_PASSWORD);

            ajaxData = userService.changePasswordByOldPassword(user, oldPassword, newPassword, language);
        } catch (ServiceException | IOException exp) {
            logger.error("Error during changing user password by old password");
            throw new CommandException(exp);
        }

        return ajaxData;
    }
}
