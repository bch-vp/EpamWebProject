package by.epam.project.controller.async;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.async.command.CommandProvider;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.controller.constant.RequestParameterKey.COMMAND;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Command command = CommandProvider.provideCommand(request.getParameter(COMMAND));
        command.execute(request, response);
    }
}
