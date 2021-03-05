package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
