package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.dao.UserDao;
import by.epam.project.model.entity.Entity;
import by.epam.project.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(User user) throws DaoException {
        return false;
    }

    public boolean add(User user, String password) throws DaoException {
        boolean update;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());

            update = statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return update;
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws DaoException {
        return false;
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> foundUser = Optional.empty();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foundUser = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return foundUser;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(createUserFromResultSet(resultSet));
            }
            return user;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
    }

    @Override
    public Optional<User> findByPhone(String phone) throws DaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return user;
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> foundUser = Optional.empty();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foundUser = Optional.of(createUserFromResultSet(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }
        return foundUser;
    }

    @Override
    public boolean activateUser(String login) throws DaoException {
        return false;
    }
}
