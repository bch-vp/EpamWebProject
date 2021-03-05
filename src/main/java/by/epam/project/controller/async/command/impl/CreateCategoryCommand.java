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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ContentKey.ERROR_NAME_NOT_UNIQUE;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.LANGUAGE;
import static by.epam.project.controller.parameter.ParameterKey.NAME;

public class CreateCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String name = (String) requestParameters.get(NAME);

            if (!ServiceValidator.isNameCorrect(name)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Optional<Category> categoryOptional = categoryService.findCategoryByName(name);
            if (categoryOptional.isPresent()) {
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_NAME_NOT_UNIQUE, language);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Category category = new Category(name);
            category = categoryService.add(category);
            String json = JsonUtil.toJson(category);
            JsonUtil.writeJsonToResponse(response, json);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
