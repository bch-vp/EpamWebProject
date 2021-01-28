package by.epam.project.model.creator;

import by.epam.project.model.entity.User;


public class UserCreator {
    public static UserCreator instance = new UserCreator();

    private UserCreator() {
    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String userLogin, String userName,
                           String userSurname, String userPhone, String userEmail) {
        User createdUser = new User(userLogin, userName, userSurname, userPhone, userEmail);
        return createdUser;
    }
}
