package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
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
import static by.epam.project.controller.parameter.ParameterKey.DATA;

public class UpdateProductInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final int SCALE = 2;

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String idString = (String) requestParameters.get(ID);
            String name = (String) requestParameters.get(NAME);
            String info = (String) requestParameters.get(INFO);
            String priceString = (String) requestParameters.get(PRICE);

            if (!ServiceValidator.isIdCorrect(idString)
                    || !ServiceValidator.isNameCorrect(name)
                    || !ServiceValidator.isInfoCorrect(info)
                    || !ServiceValidator.isPriceCorrect(priceString)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long id = Long.parseLong(idString);
            BigDecimal price = new BigDecimal(priceString);

            Product product = new Product(id, name, info, price);
            boolean isUpdated = productService.updateProductInfo(product);
            if (!isUpdated) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
