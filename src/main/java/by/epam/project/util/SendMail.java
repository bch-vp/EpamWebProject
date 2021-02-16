package by.epam.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private final static Logger log = LogManager.getLogger(SendMail.class);
    static final String SENDER_EMAIL_ADDRESS = "epam.web.project@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "epam.web.project123";
    private static final String SENDER_HOST = "smtp.gmail.com";
    private static final String SENDER_PORT = "587";
    private static final String ACTIVATION_URL = "http://localhost:8081/store_war_exploded/controller?command=activation&activation_code=";
    private static final Properties properties = new Properties();

    static {
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.smtp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }
    public static void sendActivationMailTo(String mail, long id) {
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(mail));
            message.setSubject("UGOBOSS");
            message.setText("Your activation link \n "
                    +ACTIVATION_URL+id+
                    "\n If You don't registrant on our service just ignore this message");
            Transport.send(message);
            log.info("Message activation successfully send");
        } catch (MessagingException e) {
            log.error("Failed activation mail send!"+ e);
        }
    }

    public static void sendForgotPasswordMessage(String email, String password) {
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("UGOBOSS");
            message.setText("Your new password \n "
                    +password+
                    "\n If You don't want to change password." +
                    "\n Please contact to out support.");
            Transport.send(message);
            log.info("Message forgot successfully send to "+email);
        } catch (MessagingException e) {
            log.error("Failed forgot mail send! " + e);
        }
    }
}