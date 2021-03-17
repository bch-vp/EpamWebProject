package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.ID_PRODUCT;
import static by.epam.project.controller.parameter.ParameterKey.ID_STATUS;

public class UpdateProductStatusCommand implements Command {
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String idProductString = (String) requestParameters.get(ID_PRODUCT);
            String idStatusString = (String) requestParameters.get(ID_STATUS);

            ajaxData = productService.updateProductStatus(idProductString, idStatusString);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during updating product status", exp);
        }

        return ajaxData;
    }
}
