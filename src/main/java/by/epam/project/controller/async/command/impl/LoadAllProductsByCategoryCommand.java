package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class LoadAllProductsByCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<Product> products;

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
            String category = (String) requestParameters.get(NAME);

            User user = (User) session.getAttribute(USER);

            User.Role role = User.Role.GUEST;
            if (user != null){
                role = user.getRole();
            }

            switch (role){
                case GUEST, CLIENT -> {
                    products = productService.findAllProductsByCategoryToClient(category);

                    List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);
                    if (shoppingCart != null) {
                        shoppingCart.forEach(productFromShoppingCart -> {
                            products.remove(productFromShoppingCart);
                        });
                    }
                }
                default -> {
                    products = productService.findAllProductsByCategoryToAdmin(category);
                }
            }

            String json = JsonUtil.toJson(DATA, products);
            JsonUtil.writeJsonToResponse(response, json);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
