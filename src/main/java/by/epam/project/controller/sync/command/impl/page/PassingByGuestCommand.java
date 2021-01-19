package by.epam.project.controller.sync.command.impl.page;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.constant.PagePath;

import javax.servlet.http.HttpServletRequest;

public class PassingByGuestCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.GUEST);
    }
}