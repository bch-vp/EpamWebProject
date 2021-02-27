package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.ServiceValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static by.epam.project.controller.parameter.ParameterKey.*;
import static by.epam.project.controller.parameter.ParameterKey.ADDRESS;

public class LoadAllOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        Order order1 = new Order("comm", "addr", new Date());
        Order order2 = new Order("comm2", "addr2", new Date());
        Order order3 = new Order("comm3", "addr2", new Date());
        List<Order> orders = Arrays.asList(order1, order2, order3);


        Product product1 = new Product("prod1","aegag", BigDecimal.ONE);
        Product product2 = new Product("prod2","aegag", BigDecimal.ONE);
        List<Product> products = Arrays.asList(product1, product2);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.valueToTree(orders);
        JsonNode fitstObject = arrayNode.path(1);
        try {
            ((ObjectNode)fitstObject).put("products", objectMapper.writeValueAsString(products));


        JsonNode fitst2Object = arrayNode.path(2);
        ((ObjectNode)fitst2Object).put("products", objectMapper.writeValueAsString(products));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String json = arrayNode.toString();
        try {
            JsonUtil.writeJsonToResponse(response, json);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        HttpSession session = request.getSession();
//
//        User user = (User) session.getAttribute(USER);
//        try {
//           List<Order> orders = userService.findAllOrders(user);
//
//           List<Product> products = productService.findAllOrderProducts();
//        } catch (ServiceException | IOException exp) {
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
    }
}