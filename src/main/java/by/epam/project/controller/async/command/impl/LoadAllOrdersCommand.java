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
import java.util.List;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class LoadAllOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();

    private static final String PRODUCTS = "products";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        User.Role role = user.getRole();

        String json;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            switch (role) {
                case CLIENT -> {
                    List<Order> orders = userService.findAllOrdersToClient(user);

                    ArrayNode arrayNodeOrders = objectMapper.valueToTree(orders);
                    int size = orders.size();
                    for (int i = 0; i < size; i++) {
                        JsonNode orderNode = arrayNodeOrders.path(i);

                        List<Product> products = productService.findAllOrderProducts(orders.get(i));
                        ArrayNode arrayNodeProducts = objectMapper.valueToTree(products);

                        ((ObjectNode) orderNode).putArray(PRODUCTS).addAll(arrayNodeProducts);
                    }
                    json = arrayNodeOrders.toString();
                }
                default -> {
                    List<Order> orders = userService.findAllOrdersToAdmin();

                    ArrayNode arrayNodeOrders = objectMapper.valueToTree(orders);
                    int size = orders.size();
                    for(int i=0; i<size; i++){
                        JsonNode orderNode = arrayNodeOrders.path(i);

                        Optional<User> userOrderOptional = userService.findUserByOrderId(orders.get(i).getId());
                        User userOrder = userOrderOptional.get();

                        ((ObjectNode)orderNode).put(LOGIN, user.getLogin());
                        ((ObjectNode)orderNode).put(TELEPHONE_NUMBER, user.getTelephoneNumber());
                        ((ObjectNode)orderNode).put(EMAIL, user.getEmail());

                        List<Product> products = productService.findAllOrderProducts(orders.get(i));
                        ArrayNode arrayNodeProducts = objectMapper.valueToTree(products);

                        ((ObjectNode)orderNode).putArray(PRODUCTS).addAll(arrayNodeProducts);
                    }
                    json = arrayNodeOrders.toString();
                }
            }
            JsonUtil.writeJsonToResponse(response, json);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}