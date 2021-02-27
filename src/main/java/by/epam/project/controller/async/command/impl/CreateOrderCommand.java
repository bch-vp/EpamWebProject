package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class CreateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);
        if(shoppingCart.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String comment = (String) requestParameters.get(COMMENT);
            String address = (String) requestParameters.get(ADDRESS);

            if (!ServiceValidator.isInfoCorrect(comment) ||
                    !ServiceValidator.isAddressCorrect(address)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Order order = new Order(comment, address, new Date(new Date().getTime()), Order.Status.NOT_CONFIRMED);
            userService.createOrder(user, order, shoppingCart);
            shoppingCart.clear();
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
