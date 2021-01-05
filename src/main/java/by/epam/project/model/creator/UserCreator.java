package by.epam.project.model.creator;

import by.epam.project.model.entity.User;


public class UserCreator {
    public static UserCreator instance = new UserCreator();

    private UserCreator() {
    }

    public static UserCreator getInstance() {
        return instance;
    }

    public User createUser(String userLogin, String userEmail, String userName,
                           String userSurname, String userPhone) {
        User createdUser = new User(userLogin, userEmail, userName, userSurname, userPhone);
        return createdUser;
    }
}
