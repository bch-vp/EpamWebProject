package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class CreateOrderCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String OrderComment = (String) requestParameters.get(COMMENT);
            String OrderAddress = (String) requestParameters.get(ADDRESS);

            ajaxData = userService.createOrder(user, shoppingCart, OrderAddress, OrderComment);
        } catch (ServiceException | IOException exp) {
            throw new ServiceException("Error during creating order", exp);
        }

        return ajaxData;
    }
}
