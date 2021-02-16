package by.epam.project.model.service.impl;

import by.epam.project.model.entity.User;
import by.epam.project.util.MailSenderUtil;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;
import static by.epam.project.controller.parameter.ParameterKey.LOGIN;

public class EmailServiceImpl implements by.epam.project.model.service.EmailService {
    private static final EmailServiceImpl instance = new EmailServiceImpl();

    private static final String SEPARATOR_SIGN = "/";
    private static final String AMPERSAND_SIGN = "&";
    private static final String EQUAL_SIGN = "=";
    private static final String QUESTION_MARK = "?";
    private static final String COMMAND_TYPE = "do";

    private EmailServiceImpl() {
    }

    public static EmailServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void sendConfirmationChangingPassword(User user, String subject, String body, String uniqueKey) {
        MailSenderUtil sender = new MailSenderUtil();
        String bodyEmail = String.format(body, user.getFirstName(), uniqueKey);
        sender.sendMessage(subject, bodyEmail, user.getEmail());
    }

    @Override
    public void sendActivationEmail(User user, String subject, String body,
                                    String linkApp, String command) {
        MailSenderUtil sender = new MailSenderUtil();
        String bodyEmail = String.format(body, user.getFirstName(), linkApp
                + SEPARATOR_SIGN + COMMAND_TYPE + QUESTION_MARK + COMMAND + EQUAL_SIGN + command
                + AMPERSAND_SIGN + LOGIN + EQUAL_SIGN + user.getLogin());
        sender.sendMessage(subject, bodyEmail, user.getEmail());
    }
}
