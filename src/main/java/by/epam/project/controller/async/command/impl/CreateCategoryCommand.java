package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.CategoryService;
import by.epam.project.model.service.impl.CategoryServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.LANGUAGE;
import static by.epam.project.controller.parameter.ParameterKey.NAME;

public class CreateCategoryCommand implements Command {
    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());
            String nameCategory = (String) requestParameters.get(NAME);

            ajaxData = categoryService.createCategory(nameCategory, language);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during creating new category", exp);
        }

        return ajaxData;
    }
}
