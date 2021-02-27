package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.dao.UserDao;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.util.ResultSetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;
import static by.epam.project.model.dao.SqlQuery.FIND_AVATAR_URL_BY_LOGIN;


public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final UserDaoImpl instance = new UserDaoImpl();

    private static final int CALCULUS_FROM_ONE = 1;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    public boolean add(User user, String password) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getTelephoneNumber());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getRole().ordinal() + CALCULUS_FROM_ONE);
            statement.setLong(8, user.getStatus().ordinal() + CALCULUS_FROM_ONE);

            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public boolean updateActivationStatusByLogin(String login, User.Status status) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_STATUS_BY_LOGIN)) {
            statement.setLong(1, status.ordinal() + CALCULUS_FROM_ONE);
            statement.setString(2, login);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public Optional<String> findAvatarURLByLogin(String login) throws DaoException {
        Optional<String> stringOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_AVATAR_URL_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                stringOptional = Optional.ofNullable(resultSet.getString(AVATAR_URL));
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return stringOptional;
    }

    @Override
    public boolean removeAvatarByLogin(String login) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_AVATAR_BY_LOGIN)) {
            statement.setString(1, login);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isRemoved;
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_LOGIN)) {
            statement.setString(1, password);
            statement.setString(2, login);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUser(User newUser, String oldLogin) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_BY_LOGIN)) {
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getFirstName());
            statement.setString(3, newUser.getLastName());
            statement.setString(4, newUser.getTelephoneNumber());
            statement.setString(5, newUser.getEmail());
            statement.setString(6, oldLogin);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAvatarURLByLogin(String login, String fileURL) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_AVATAR_URL_BY_LOGIN)) {
            statement.setString(1, fileURL);
            statement.setString(2, login);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> userOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                userOptional = Optional.of(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return userOptional;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                users.add(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return users;
    }

    @Override
    public List<User> findAllClients() throws DaoException {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CLIENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                users.add(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Optional<User> productOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                productOptional = Optional.of(user);
            }

        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }

        return productOptional;
    }

    @Override
    public boolean updateUserStatus(long idUser, long idStatus) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setLong(1, idStatus);
            statement.setLong(2, idUser);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public Optional<User.Status> findStatusById(long id) throws DaoException {
        Optional<User.Status> statusOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_STATUS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User.Status status = User.Status.valueOf(resultSet.getString(NAME));
                statusOptional = Optional.of(status);
            }

        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }

        return statusOptional;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> userOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                userOptional = Optional.of(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findByTelephoneNumber(String phone) throws DaoException {
        Optional<User> userOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                userOptional = Optional.of(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return userOptional;
    }

    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        Optional<String> passwordOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passwordOptional = Optional.of(resultSet.getString(PASSWORD));
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return passwordOptional;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = ResultSetUtil.toUser(resultSet);
                userOptional = Optional.of(user);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return userOptional;
    }

    @Override
    public boolean createOrder(User user, Order order, List<Product> products) throws DaoException {
        boolean isUpdated;

        Connection connection = ConnectionPool.getInstance().getConnection();
        try (connection;
             PreparedStatement statementInsertOrder = connection.prepareStatement(SqlQuery.INSERT_ORDER,
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementInsertOrderProducts = connection.prepareStatement(SqlQuery.INSERT_ORDER_PRODUCTS);
             PreparedStatement statementUpdateProductStatus = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_STATUS)) {
            try {
                connection.setAutoCommit(false);

                statementInsertOrder.setString(1, order.getComment());
                statementInsertOrder.setString(2, order.getAddress());
                statementInsertOrder.setLong(3, order.getDateCreatedAt().getTime());
                statementInsertOrder.setBigDecimal(4, order.getTotalPrice());
                statementInsertOrder.setLong(5, user.getId());
                statementInsertOrder.setLong(6, order.getStatus().ordinal());
                statementInsertOrder.executeUpdate();
                ResultSet resultSet = statementInsertOrder.getGeneratedKeys();
                resultSet.next();
                long OrderGeneratedId = resultSet.getLong(1);

                for (Product product : products) {
                    statementUpdateProductStatus.setLong(1, Product.Status.INACTIVE.ordinal()
                            + CALCULUS_FROM_ONE);
                    statementUpdateProductStatus.setLong(2, product.getId());
                    statementUpdateProductStatus.addBatch();

                    statementInsertOrderProducts.setLong(1, OrderGeneratedId);
                    statementInsertOrderProducts.setLong(2, product.getId());
                    statementInsertOrderProducts.addBatch();
                }
                statementUpdateProductStatus.executeBatch();
                statementInsertOrderProducts.executeBatch();

                connection.commit();
            } catch (SQLException exp) {
                logger.error(exp);
                connection.rollback();
                throw new DaoException(exp);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return true;
    }

    @Override
    public List<Order> findAllOrders(User user) throws DaoException {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ORDERS_BY_USER_ID)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = ResultSetUtil.toOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException exp) {
            logger.error(exp);
            throw new DaoException(exp);
        }
        return orders;
    }
}
