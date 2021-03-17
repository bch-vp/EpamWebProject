package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class SignInCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final int EMPTY_PRIMITIVE = 0;

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String login = (String) requestParameters.get(LOGIN);
            String password = (String) requestParameters.get(PASSWORD);

            ajaxData = userService.signIn(login, password, language);
            if (ajaxData.getStatusHttp() != EMPTY_PRIMITIVE) {
                return ajaxData;
            }

            session.setAttribute(USER, ajaxData.getDataSession().get(USER));
            session.setAttribute(SHOPPING_CART, new ArrayList<Product>());
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during sign in", exp);
        }

        return ajaxData;
    }
}
