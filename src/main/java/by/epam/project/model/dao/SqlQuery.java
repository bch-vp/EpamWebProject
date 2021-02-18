package by.epam.project.model.dao;

public class SqlQuery {
    public static final String ADD_USER = "INSERT INTO users (login, password, first_name, last_name, " +
            "telephone_number, email, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE BINARY login = ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email, role, is_activated FROM users WHERE BINARY login = ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email, role, is_activated FROM users WHERE BINARY email = ?";
    public static final String FIND_USER_BY_PHONE = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email, role, is_activated FROM users WHERE telephone_number = ?";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT login, password, first_name, last_name, " +
            "telephone_number, email, role, is_activated FROM users WHERE BINARY login = ? AND BINARY password = ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE BINARY login = ?";
    public static final String UPDATE_USER_BY_LOGIN = "UPDATE users SET login = ?, first_name = ?," +
            " last_name = ?, telephone_number = ?, email = ? WHERE BINARY login = ?";
    public static final String FIND_AVATAR_BY_LOGIN = "SELECT avatar FROM users WHERE BINARY login = ?";
    public static final String UPDATE_AVATAR_BY_LOGIN = "UPDATE users SET avatar = ? WHERE BINARY login = ?";
    public static final String REMOVE_AVATAR_BY_LOGIN = "UPDATE users SET avatar = NULL WHERE BINARY login = ?";
    public static final String UPDATE_ACTIVATION_STATUS_BY_LOGIN = "UPDATE users SET is_activated = ? " +
            "WHERE BINARY login = ?";

    public static final String FIND_ALL_CATEGORIES = "SELECT name FROM categories";

    public static final String FIND_ALL_PRODUCTS_BY_CATEGORY_TO_CLIENT = "SELECT products.name AS name, products.info " +
            "AS info, products.status AS status, products.price AS price FROM products " +
            "JOIN categories on categories.id = products.category_id WHERE categories.name = ? AND " +
            "(products.status != 'BLOCKED')";
    public static final String FIND_PRODUCT_BY_NAME = "SELECT name, info, status, price, " +
            "FROM products WHERE BINARY name = ?";



    private SqlQuery() {
    }
}
