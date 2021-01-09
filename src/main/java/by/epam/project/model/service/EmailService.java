package by.epam.project.model.service;

import by.epam.project.model.entity.User;

public interface EmailService {
    void sendActivationEmail(User user, String subject, String body, String link);
}
