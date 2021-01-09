package by.epam.project.model.service.impl;

import by.epam.project.model.entity.User;
import by.epam.project.model.service.EmailService;
import by.epam.project.util.MailSender;

public class EmailServiceImpl implements EmailService {
    private static final EmailServiceImpl instance =
            new EmailServiceImpl();

    private EmailServiceImpl() {
    }

    public static EmailServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void sendActivationEmail(User user, String subject, String body, String link) {
        MailSender sender = new MailSender();
        String bodyEmail = String.format(body, user.getName(), link + user.getLogin());
        sender.sendMessage(subject, bodyEmail, user.getEmail());
    }
}
