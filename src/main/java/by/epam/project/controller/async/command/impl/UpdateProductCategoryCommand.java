package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class UpdateProductCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final ProductService productService = ProductServiceImpl.getInstance();
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String idProductString = (String) requestParameters.get(ID_PRODUCT);
            String idCategoryString = (String) requestParameters.get(ID_CATEGORY);
            if (!ServiceValidator.isIdCorrect(idProductString)
                    || ServiceValidator.isIdCorrect(idCategoryString)){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long idProduct = Long.parseLong(idProductString);
            long idCategory = Long.parseLong(idProductString);

            Optional<Product> productOptional = productService.findProductById(idProduct);
            if (productOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Optional<Category> categoryOptional = categoryService.findCategoryById(idCategory);
            if (categoryOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Category category = categoryOptional.get();

            boolean isUpdated = productService.updateProductCategory(idProduct, category.getId());
            if (!isUpdated) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
