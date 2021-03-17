package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class UpdateProfileCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final int EMPTY_PRIMITIVE = 0;

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String newLogin = (String) requestParameters.get(LOGIN);
            String newFirstName = (String) requestParameters.get(FIRST_NAME);
            String newLastName = (String) requestParameters.get(LAST_NAME);
            String newTelephoneNumber = (String) requestParameters.get(TELEPHONE_NUMBER);
            String newEmail = (String) requestParameters.get(EMAIL);

            ajaxData = userService.updateProfile(user, newLogin, newFirstName, newLastName, newTelephoneNumber,
                    newEmail, language);
            if(ajaxData.getStatusHttp() != EMPTY_PRIMITIVE){
                return ajaxData;
            }

            User newUser = new User(user.getId(), newLogin, newFirstName, newLastName, newTelephoneNumber,
                    newEmail, user.getRole(), user.getStatus());
            session.setAttribute(USER, newUser);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during updating user profile", exp);
        }

        return ajaxData;
    }
}
