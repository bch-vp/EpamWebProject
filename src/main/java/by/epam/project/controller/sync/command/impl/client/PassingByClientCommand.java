package by.epam.project.controller.sync.command.impl.client;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.parameter.PagePath;

import javax.servlet.http.HttpServletRequest;

public class PassingByClientCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.CLIENT);
    }
}
