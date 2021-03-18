package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class CreateProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String idCategoryString = (String) requestParameters.get(ID_CATEGORY);
            String name = (String) requestParameters.get(NAME);
            String info = (String) requestParameters.get(INFO);
            String priceString = (String) requestParameters.get(PRICE);

            ajaxData = productService.createProduct(idCategoryString, name, info, priceString, language);
        } catch (ServiceException | IOException exp) {
            logger.error("Error during creating product");
            throw new CommandException(exp);
        }

        return ajaxData;
    }
}
