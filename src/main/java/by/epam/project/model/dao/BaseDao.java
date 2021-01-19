package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.model.creator.UserCreator;
import by.epam.project.model.entity.Entity;
import by.epam.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.project.controller.constant.ParameterKey.*;

public interface BaseDao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    default User createUserFromResultSet(ResultSet resultSet) throws DaoException {
        UserCreator creator = UserCreator.getInstance();

        try {
            String userLogin = resultSet.getString(USER_LOGIN);
            String userName = resultSet.getString(USER_NAME);
            String userSurname = resultSet.getString(USER_SURNAME);
            String userPhone = resultSet.getString(USER_PHONE);
            String userEmail = resultSet.getString(USER_EMAIL);

            User createdUser = creator.createUser(userLogin, userEmail, userName,
                    userSurname, userPhone);
            return createdUser;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating user from resultSet", exp);
        }
    }

    boolean add(T t) throws DaoException;
}
