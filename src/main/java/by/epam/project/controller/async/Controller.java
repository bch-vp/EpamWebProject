package by.epam.project.controller.async;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.async.command.CommandProvider;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;

public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Command command = CommandProvider.provideCommand(request.getParameter(COMMAND));
        try {
            AjaxData ajaxData = command.execute(request, response);
            JsonUtil.writeAjaxDataToResponse(response, ajaxData);
        } catch (CommandException | IOException exp) {
            logger.error(exp.toString());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
