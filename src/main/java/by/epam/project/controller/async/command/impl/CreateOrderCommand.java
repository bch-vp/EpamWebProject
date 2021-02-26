package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static by.epam.project.controller.parameter.ParameterKey.SHOPPING_CART;
import static by.epam.project.controller.parameter.ParameterKey.USER;

public class CreateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(USER);
        List<Product> shoppingCart = (ArrayList<Product>) session.getAttribute(SHOPPING_CART);

//        try {
//            userService.createOrder(user, products);
//        }catch (ServiceException exp){
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }

    }
}
