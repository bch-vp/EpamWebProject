package by.epam.project.model.dao;

public class SqlQuery {
    public static final String ADD_USER = "INSERT INTO users (login, password, first_name, last_name, telephone_number, email" +
            ") VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email FROM users WHERE login = ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email FROM users WHERE email = ?";
    public static final String FIND_USER_BY_PHONE = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email FROM users WHERE telephone_number = ?";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email FROM users WHERE login = ? AND password = ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE login = ?";

    private SqlQuery() {
    }
}
