package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;
import by.epam.project.model.service.CategoryService;
import by.epam.project.model.service.impl.CategoryServiceImpl;
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

import static by.epam.project.controller.parameter.ParameterKey.ID;
import static by.epam.project.controller.parameter.ParameterKey.NAME;

public class RemoveCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    private static final String OTHERS = "others";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
            String idCategoryString = (String) requestParameters.get(ID);
            String newName = (String) requestParameters.get(NAME);

            if (!ServiceValidator.isIdCorrect(idCategoryString)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long idCategory = Long.parseLong(idCategoryString);
            Optional<Category> categoryOptional = categoryService.findCategoryById(idCategory);
            if (categoryOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Category category = categoryOptional.get();
            if (category.getName().equals(OTHERS)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            categoryService.removeCategoryById(idCategory);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
