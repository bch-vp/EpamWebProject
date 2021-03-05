package by.epam.project.util;

import by.epam.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static by.epam.project.controller.parameter.ParameterKey.*;


public class MailSenderUtil {
    private final static Logger logger = LogManager.getLogger();
    private static final String ADDRESS = "epam.web.project@gmail.com";
    private static final String PASSWORD = "epam.web.project123";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final Properties properties = new Properties();

    private static final String SEPARATOR_SIGN = "/";
    private static final String AMPERSAND_SIGN = "&";
    private static final String EQUAL_SIGN = "=";
    private static final String QUESTION_MARK = "?";
    private static final String COMMAND_TYPE = "do";

    static {
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.from", ADDRESS);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }

    private MailSenderUtil() {
    }

    public static void sendMessage(String subject, String body, String email) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ADDRESS, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("epam.web.project@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject, ENCODING);
            message.setText(body, ENCODING);
            Transport.send(message);
        } catch (MessagingException exp) {
            logger.error("Error sending a message", exp);
        }
    }

    public static void sendConfirmationChangingPassword(User user, String subject, String body, String uniqueKey) {
        String bodyEmail = String.format(body, user.getFirstName(), uniqueKey);
        sendMessage(subject, bodyEmail, user.getEmail());
    }

    public static void sendActivationEmail(User user, String subject, String body, String linkApp, String command) {
        String bodyEmail = String.format(body, user.getFirstName(), linkApp
                + SEPARATOR_SIGN + COMMAND_TYPE + QUESTION_MARK + COMMAND + EQUAL_SIGN + command
                + AMPERSAND_SIGN + LOGIN + EQUAL_SIGN + user.getLogin());
        sendMessage(subject, bodyEmail, user.getEmail());
    }
}
