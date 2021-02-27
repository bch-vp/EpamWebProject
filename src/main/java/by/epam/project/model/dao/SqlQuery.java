package by.epam.project.model.dao;

public class SqlQuery {
    public static final String ADD_USER = "INSERT INTO users (login, password, first_name, last_name, " +
            "telephone_number, email, role_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE BINARY login = ?";

    public static final String FIND_USER_BY_LOGIN = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE BINARY login = ?";

    public static final String FIND_USER_BY_ID = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE users.id = ?";

    public static final String FIND_ALL_USERS = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id";

    public static final String FIND_ALL_CLIENTS = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE user_roles.name != 'ADMIN'";

    public static final String FIND_USER_BY_EMAIL = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE BINARY email = ?";

    public static final String FIND_USER_BY_PHONE = "SELECT users.id AS id, login, password, first_name, last_name, " +
            "telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE BINARY telephone_number = ?";

    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT users.id AS id, login, password, first_name, " +
            "last_name, telephone_number, email, user_roles.name AS role, user_statuses.name AS status FROM users " +
            "JOIN user_statuses ON users.status_id = user_statuses.id " +
            "JOIN user_roles ON users.role_id = user_roles.id " +
            "WHERE BINARY login = ? AND BINARY password = ?";

    public static final String FIND_USER_STATUS_BY_ID = "SELECT name FROM user_statuses WHERE id = ?";

    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE BINARY login = ?";

    public static final String UPDATE_USER_BY_LOGIN = "UPDATE users SET login = ?, first_name = ?," +
            " last_name = ?, telephone_number = ?, email = ? WHERE BINARY login = ?";

    public static final String FIND_AVATAR_URL_BY_LOGIN = "SELECT avatar_url FROM users WHERE BINARY login = ?";

    public static final String UPDATE_AVATAR_URL_BY_LOGIN = "UPDATE users SET avatar_url = ? WHERE BINARY login = ?";

    public static final String REMOVE_AVATAR_BY_LOGIN = "UPDATE users SET avatar_url = NULL WHERE BINARY login = ?";

    public static final String UPDATE_STATUS_BY_LOGIN = "UPDATE users SET status_id = ? WHERE BINARY login = ?";

    public static final String UPDATE_USER_STATUS = "UPDATE users SET status_id = ? WHERE id = ?";


    public static final String ADD_CATEGORY =  "INSERT INTO categories(name) VALUES (?) ";

    public static final String FIND_ALL_CATEGORIES = "SELECT id, name FROM categories";

    public static final String FIND_CATEGORY_BY_NAME = "SELECT id, name FROM categories WHERE name = ?";

    public static final String FIND_CATEGORY_BY_ID = "SELECT id, name FROM categories WHERE id = ?";

    public static final String UPDATE_CATEGORY_NAME_BY_ID = "UPDATE categories SET name = ? WHERE id = ?";

    public static final String REMOVE_CATEGORY_BY_ID = "DELETE FROM categories WHERE id = ?";



    public static final String ADD_PRODUCT = "INSERT INTO products(name, info, price, category_id, status_id)" +
            "VALUES (?, ?, ?, ?, ?) ";

    public static final String UPDATE_PRODUCT_IMAGE_URL = "UPDATE products SET image_url = ? WHERE BINARY name = ?";

    public static final String FIND_PRODUCT_IMAGE_BY_NAME = "SELECT image_url FROM products WHERE BINARY name = ?";

    public static final String FIND_ALL_PRODUCTS_BY_CATEGORY_TO_CLIENT = "SELECT products.id as id, products.name AS name, " +
            "products.info AS info, product_statuses.name AS status, products.price AS price, " +
            "products.image_url as image_url FROM products " +
            "JOIN product_statuses ON products.status_id = product_statuses.id " +
            "JOIN categories ON products.category_id = categories.id " +
            "WHERE categories.name = ? AND product_statuses.name != 'BLOCKED'";

    public static final String FIND_ALL_PRODUCTS_BY_CATEGORY_TO_ADMIN = "SELECT products.id as id, products.name AS name, " +
            "products.info AS info, product_statuses.name AS status, products.price AS price, " +
            "products.image_url as image_url FROM products " +
            "JOIN product_statuses ON products.status_id = product_statuses.id " +
            "JOIN categories ON products.category_id = categories.id " +
            "WHERE categories.name = ?";

    public static final String FIND_PRODUCT_BY_NAME = "SELECT products.id as id, products.name AS name, " +
            "products.info AS info, product_statuses.name AS status, products.price AS price, " +
            "products.image_url as image_url FROM products " +
            "JOIN product_statuses ON products.status_id = product_statuses.id " +
            "WHERE products.name = ?";

    public static final String FIND_PRODUCT_BY_ID = "SELECT products.id as id, products.name AS name, " +
            "products.info AS info, product_statuses.name AS status, products.price AS price, " +
            "products.image_url as image_url FROM products " +
            "JOIN product_statuses ON products.status_id = product_statuses.id " +
            "WHERE products.id = ?";

    public static final String FIND_PRODUCT_STATUS_BY_ID = "SELECT name FROM product_statuses WHERE id = ?";

    public static final String UPDATE_PRODUCT_INFO = "UPDATE products SET name = ?, info = ?, price = ? " +
            "WHERE id = ?";

    public static final String UPDATE_PRODUCT_CATEGORY = "UPDATE products SET category_id = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_STATUS = "UPDATE products SET status_id = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_CATEGORY_BY_ID_TO_OTHERS =
            "UPDATE products SET category_id = 1 WHERE category_id = ?";


    public static final String INSERT_ORDER = "INSERT INTO orders(comment, address, created_at, total_price, " +
            "user_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String INSERT_ORDER_PRODUCTS = "INSERT INTO orders_products(order_id, product_id)" +
            " VALUES (?, ?)";

    public static final String FIND_ALL_ORDERS_BY_USER_ID = "SELECT comment, address, created_at, total_price, " +
            "order_statuses.name as status FROM orders " +
            "JOIN order_statuses ON orders.status_id = order_statuses.id " +
            "WHERE user_id = ?";

    public static final String FIND_ALL_ORDER_PRODUCTS_BY_ID = "SELECT products.name as name, info, price, image_url," +
            " product_statuses.name as status FROM products " +
            "JOIN product_statuses ON products.status_id = product_statuses.id " +
            "JOIN orders_products ON products.id = orders_products.product_id " +
            "WHERE order_id = ?";

    private SqlQuery() {
    }
}
