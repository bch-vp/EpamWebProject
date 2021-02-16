package by.epam.project.util;


import by.epam.project.exception.DaoException;
import by.epam.project.model.entity.Category;
import by.epam.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ResultSetUtil {
    private ResultSetUtil(){}

    public static User toUser(ResultSet resultSet) throws DaoException {
        try {
            String login = resultSet.getString(LOGIN);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            String telephoneNumber = resultSet.getString(TELEPHONE_NUMBER);
            String email = resultSet.getString(EMAIL);
            String roleName = resultSet.getString(ROLE);
            User.Role role = User.Role.valueOf(roleName);
            boolean isActivated = resultSet.getBoolean(IS_ACTIVATED);

            User user =new User(login, firstName, lastName, telephoneNumber, email, role, isActivated);
            return user;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating user from resultSet", exp);
        }
    }

    public static Category toCategory(ResultSet resultSet) throws DaoException {
        try {
            String name = resultSet.getString(NAME);

            Category category =new Category(name);
            return category;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating category from resultSet", exp);
        }
    }
}
