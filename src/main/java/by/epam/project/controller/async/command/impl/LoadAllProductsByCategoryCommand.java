package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class LoadAllProductsByCategoryCommand implements Command {
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());
            String categoryName = (String) requestParameters.get(NAME);

            User user = (User) session.getAttribute(USER);
            User.Role userRole = User.Role.GUEST;
            if (user != null) {
                userRole = user.getRole();
            }

            ajaxData = productService.loadAllProductsByCategory(userRole, categoryName, shoppingCart);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during loading all products by category", exp);
        }

        return ajaxData;
    }
}
