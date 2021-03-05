package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ContentKey.ERROR_NAME_NOT_UNIQUE;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.*;

public class CreateProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String idCategoryString = (String) requestParameters.get(ID_CATEGORY);
            String name = (String) requestParameters.get(NAME);
            String info = (String) requestParameters.get(INFO);
            String priceString = (String) requestParameters.get(PRICE);

            if (!ServiceValidator.isIdCorrect(idCategoryString)
                    || !ServiceValidator.isNameCorrect(name)
                    || !ServiceValidator.isInfoCorrect(info)
                    || !ServiceValidator.isPriceCorrect(priceString)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Optional<Product> productOptional = productService.findProductByName(name);
            if (productOptional.isPresent()) {
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_NAME_NOT_UNIQUE, language);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long idCategory = Long.parseLong(idCategoryString);
            BigDecimal price = new BigDecimal(priceString);

            Product product = new Product(name, info, Product.Status.ACTIVE, price);
            productService.add(product, idCategory);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
