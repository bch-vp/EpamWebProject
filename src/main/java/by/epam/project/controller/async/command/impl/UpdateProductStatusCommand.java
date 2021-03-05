package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.CategoryService;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.CategoryServiceImpl;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.ID_PRODUCT;
import static by.epam.project.controller.parameter.ParameterKey.ID_STATUS;

public class UpdateProductStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String idProductString = (String) requestParameters.get(ID_PRODUCT);
            String idStatusString = (String) requestParameters.get(ID_STATUS);
            if (!ServiceValidator.isIdCorrect(idProductString)
                    || !ServiceValidator.isIdCorrect(idStatusString)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long idProduct = Long.parseLong(idProductString);
            long idStatus = Long.parseLong(idStatusString);

            Optional<Product> productOptional = productService.findProductById(idProduct);
            if (productOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Optional<Product.Status> statusOptional = productService.findStatusById(idStatus);
            if (statusOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            productService.updateProductStatus(idProduct, idStatus);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
