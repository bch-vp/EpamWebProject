package by.epam.project.model.dao;

public class SqlQuery {
    public static final String ADD_USER = "INSERT INTO users (login, password, name, surname, phone, email" +
            ") VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT login, email, name, surname, " +
            "phone FROM users WHERE login = ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT login, email, name, surname, " +
            "phone FROM users WHERE email = ?";
    public static final String FIND_USER_BY_PHONE = "SELECT login, email, name, surname, " +
            "phone FROM users WHERE phone = ?";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT login, email, name, surname, " +
            "phone FROM users WHERE login = ? AND password = ?";


    private SqlQuery() {
    }
}
