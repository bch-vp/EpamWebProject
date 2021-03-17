package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ChangePasswordByEmailCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        String sessionUniqueKey = (String) session.getAttribute(UNIQUE_KEY);
        long timeCreated = (long) session.getAttribute(TIME_CREATED);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String login = (String) requestParameters.get(LOGIN);
            String email = (String) requestParameters.get(EMAIL);
            String newPassword = (String) requestParameters.get(NEW_PASSWORD);
            String requestUniqueKey = (String) requestParameters.get(UNIQUE_KEY);

            ajaxData = userService.changePasswordByEmail(login, newPassword, email, sessionUniqueKey, requestUniqueKey,
                    timeCreated, language);

            if (ajaxData.getStatusHttp() == HttpServletResponse.SC_UNAUTHORIZED) {
                int uniqueKey = (int) ajaxData.getDataSession().get(UNIQUE_KEY);
                session.setAttribute(TIME_CREATED, System.currentTimeMillis());
                session.setAttribute(UNIQUE_KEY, uniqueKey);
            } else if (ajaxData.getStatusHttp() == HttpServletResponse.SC_REQUEST_TIMEOUT) {
                session.removeAttribute(UNIQUE_KEY);
            }
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during changing user password by email", exp);
        }

        return ajaxData;


    }
}
