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
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.ID;

public class RemoveCategoryCommand implements Command {
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());
            String idCategoryString = (String) requestParameters.get(ID);

            ajaxData = categoryService.removeCategory(idCategoryString);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during removing category", exp);
        }

        return ajaxData;
    }
}
