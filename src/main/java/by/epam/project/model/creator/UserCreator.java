package by.epam.project.model.creator;

import by.epam.project.model.entity.User;


public class UserCreator {
    public static UserCreator instance = new UserCreator();

    private UserCreator() {
    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String login, String firstName, String lastName, String telephoneNumber,
                           String email, int role, boolean isActivated) {
        User createdUser = new User(login, firstName, lastName, telephoneNumber, email, role, isActivated);
        return createdUser;
    }
}
