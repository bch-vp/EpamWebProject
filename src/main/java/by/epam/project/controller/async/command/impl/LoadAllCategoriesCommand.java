package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.CategoryDao;
import by.epam.project.model.dao.impl.CategoryDaoImpl;
import by.epam.project.model.entity.Category;
import by.epam.project.model.service.CategoryService;
import by.epam.project.model.service.impl.CategoryServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.project.controller.parameter.ParameterKey.DATA;


public class LoadAllCategoriesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Category> categories;
        try {
            categories = categoryService.findAllCategories();

            String json = JsonUtil.toJson(DATA, categories);
            JsonUtil.writeJsonToResponse(response, json);
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
