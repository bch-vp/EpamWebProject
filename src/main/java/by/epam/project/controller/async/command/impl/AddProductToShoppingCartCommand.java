package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.project.controller.parameter.Parameter.NAME;
import static by.epam.project.controller.parameter.Parameter.SHOPPING_CART;

/**
 * The type Add product to shopping cart command.
 */
public class AddProductToShoppingCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

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
            logger.error("Error during adding product to shopping cart");
            throw new CommandException(exp);
        }

        return ajaxData;
    }
}
