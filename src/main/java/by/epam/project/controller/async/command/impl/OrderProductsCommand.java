package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
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

public class OrderProductsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        String comment = (String) session.getAttribute(COMMENT);
//        String address = (String) session.getAttribute(ADDRESS);
//
//        List<Product> products = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);
//
//        try {
//            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
//
//            String login = (String) requestParameters.get(LOGIN);
//            String password = (String) requestParameters.get(PASSWORD);
//        } catch (ServiceException | IOException exp) {
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
    }
}
