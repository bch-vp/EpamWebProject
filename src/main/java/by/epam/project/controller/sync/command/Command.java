package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
