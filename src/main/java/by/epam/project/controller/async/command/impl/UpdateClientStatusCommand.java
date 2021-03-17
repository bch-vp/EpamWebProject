package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static by.epam.project.controller.parameter.ParameterKey.ID_STATUS;
import static by.epam.project.controller.parameter.ParameterKey.ID_USER;

public class UpdateClientStatusCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData;

        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream());

            String idUserString = (String) requestParameters.get(ID_USER);
            String idStatusString = (String) requestParameters.get(ID_STATUS);

            ajaxData = userService.updateClientStatus(idUserString, idStatusString);
        } catch (ServiceException | IOException exp) {
            throw new CommandException("Error during updating client status", exp);
        }

        return ajaxData;
    }
}

