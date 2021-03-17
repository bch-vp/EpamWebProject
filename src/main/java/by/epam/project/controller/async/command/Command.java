package by.epam.project.controller.async.command;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException;
}
