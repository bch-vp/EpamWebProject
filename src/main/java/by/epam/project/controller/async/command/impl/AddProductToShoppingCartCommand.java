package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static by.epam.project.controller.parameter.ParameterKey.NAME;
import static by.epam.project.controller.parameter.ParameterKey.SHOPPING_CART;

public class AddProductToShoppingCartCommand implements Command {
    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());
            String productName = (String) requestParameters.get(NAME);

            ajaxData = productService.addProductToShoppingCart(shoppingCart, productName);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during adding product to shopping cart", exp);
        }

        return ajaxData;
    }
}
