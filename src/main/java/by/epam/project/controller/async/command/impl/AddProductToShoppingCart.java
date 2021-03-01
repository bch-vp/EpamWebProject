package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
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

import static by.epam.project.controller.parameter.ParameterKey.*;

public class AddProductToShoppingCart implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final ProductService productService = ProductServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
            String productName = (String) requestParameters.get(NAME);

            Optional<Product> productOptional = productService.findProductByName(productName);
            if(productOptional.isEmpty()){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Product product = productOptional.get();
            if(shoppingCart.contains(product)){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            shoppingCart.add(product);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
