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

public class SignUpCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String login = (String) requestParameters.get(LOGIN);
            String password = (String) requestParameters.get(PASSWORD);
            String firstName = (String) requestParameters.get(FIRST_NAME);
            String lastName = (String) requestParameters.get(LAST_NAME);
            String telephoneNumber = (String) requestParameters.get(TELEPHONE_NUMBER);
            String email = (String) requestParameters.get(EMAIL);

            String URL = request.getRequestURL().toString();
            String URI = request.getRequestURI();
            String confirmationLink = URL.replace(URI, EMPTY_STRING);

            ajaxData = userService.signUp(login, password, firstName, lastName, telephoneNumber,
                    email, confirmationLink, language);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during sign up", exp);
        }

        return ajaxData;
    }
}