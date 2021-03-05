package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.model.entity.Product;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.project.controller.parameter.ParameterKey.DATA;
import static by.epam.project.controller.parameter.ParameterKey.SHOPPING_CART;

public class LoadShoppingCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);
            if (shoppingCart == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String json = JsonUtil.toJson(DATA, shoppingCart);
            JsonUtil.writeJsonToResponse(response, json);
        } catch (IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
