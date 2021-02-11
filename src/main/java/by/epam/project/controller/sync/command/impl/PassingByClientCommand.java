package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.SyncCommand;
import by.epam.project.controller.parameter.PagePath;

import javax.servlet.http.HttpServletRequest;

public class PassingByClientSyncCommand implements SyncCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.PASSING_BY_CLIENT);
    }
}
