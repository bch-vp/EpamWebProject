package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.model.creator.UserCreator;
import by.epam.project.model.entity.Entity;
import by.epam.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.project.controller.parameter.ParameterKey.*;

public interface BaseDao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    default User createUserFromResultSet(ResultSet resultSet) throws DaoException {
        UserCreator creator = UserCreator.getInstance();

        try {
            String login = resultSet.getString(LOGIN);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            String telephoneNumber = resultSet.getString(TELEPHONE_NUMBER);
            String email = resultSet.getString(EMAIL);
            int role = resultSet.getInt(ROLE);
            boolean isActivated = resultSet.getBoolean(IS_ACTIVATED);

            User createdUser = creator.createUser(login, firstName,
                    lastName, telephoneNumber, email, role, isActivated);
            return createdUser;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating user from resultSet", exp);
        }
    }

    boolean add(T t) throws DaoException;
}
