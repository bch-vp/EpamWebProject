package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.util.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PassingByGuestCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final EmailServiceImpl emailService = EmailServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        SendMail.sendActivationMailTo("ilya.murin.work@gmail.com", 2);
        return new Router(PagePath.PASSING_BY_GUEST);
    }
}