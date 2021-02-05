package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;

public interface Command {
    Router execute(HttpServletRequest request);
}
