package by.epam.project.controller.command.impl;

import by.epam.project.controller.Router;
import by.epam.project.controller.command.Command;
import by.epam.project.controller.command.PagePath;
import by.epam.project.controller.command.PropertiesContentKey;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.EmailService;
import by.epam.project.model.service.impl.EmailServiceImpl;
import by.epam.project.util.ContentUtil;

import javax.servlet.http.HttpServletRequest;

public class SendMail implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String emailSubjectWithLocale = ContentUtil.getWithLocale("ru", PropertiesContentKey.EMAIL_SUBJECT);
        String emailBodyWithLocale = ContentUtil.getWithLocale("ru", PropertiesContentKey.EMAIL_BODY);

        User user = new User("login", "name", "surname" , "ph", "ilia.ilia.ilia201@gmail.com");
        EmailServiceImpl emailService = EmailServiceImpl.getInstance();
        emailService.sendActivationEmail(user, emailSubjectWithLocale,
                emailBodyWithLocale, PagePath.EMAIL_ACTIVATION_LINK);
        return new Router(PagePath.HOME);

    }
}
