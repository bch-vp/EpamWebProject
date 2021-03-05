package by.epam.project.controller.async.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String COMMAND_NAME = "command";
    String CONTENT_TYPE = "application/json";
    String ENCODING = "UTF-8";

    void execute(HttpServletRequest request, HttpServletResponse response);
}
