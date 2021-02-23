package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.dao.UserDao;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.util.ResultSetUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;
import static by.epam.project.model.dao.SqlQuery.FIND_AVATAR_BY_LOGIN;


public class UserDaoImpl implements UserDao {
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
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public Optional<byte[]> findAvatarByLogin(String login) throws DaoException {
        Optional<byte[]> bytesOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_AVATAR_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bytesOptional =  Optional.ofNullable(resultSet.getBytes(AVATAR));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return bytesOptional;
    }

    @Override
    public boolean removeAvatarByLogin(String login) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_AVATAR_BY_LOGIN)) {
            statement.setString(1, login);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
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
            throw new DaoException(exp);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAvatarByLogin(String login, InputStream inputStream) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_AVATAR_BY_LOGIN)) {
            statement.setBlob(1, inputStream);
            statement.setString(2, login);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
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

        } catch (
                SQLException exp) {
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

        } catch (
                SQLException exp) {
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
            throw new DaoException(exp);
        }
        return userOptional;
    }

    @Override
    public boolean activateUser(String login) throws DaoException {
        return false;
    }
}
