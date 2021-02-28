package by.epam.project.util;

import by.epam.project.exception.DaoException;
import by.epam.project.model.entity.Category;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ResultSetUtil {
    private ResultSetUtil(){}

    public static User toUser(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ID);
            String login = resultSet.getString(LOGIN);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            String telephoneNumber = resultSet.getString(TELEPHONE_NUMBER);
            String email = resultSet.getString(EMAIL);
            String roleName = resultSet.getString(ROLE);
            User.Role role = User.Role.valueOf(roleName);
            String statusName = resultSet.getString(STATUS);
            User.Status status = User.Status.valueOf(statusName);

            User user =new User(id, login, firstName, lastName, telephoneNumber, email, role, status);
            return user;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating user from resultSet", exp);
        }
    }

    public static Category toCategory(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ID);
            String name = resultSet.getString(NAME);

            Category category =new Category(id, name);
            return category;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating category from resultSet", exp);
        }
    }

    public static Product toProduct(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ID);
            String name = resultSet.getString(NAME);
            String info = resultSet.getString(INFO);
            String statusName = resultSet.getString(STATUS);
            Product.Status status = Product.Status.valueOf(statusName);
            BigDecimal price = resultSet.getBigDecimal(PRICE);
            String imageURL = resultSet.getString(IMAGE_URL);

            Product product =new Product(id, name, info, status, price, imageURL);
            return product;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating product from resultSet", exp);
        }
    }

    public static Order toOrder(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ID);
            String address = resultSet.getString(ADDRESS);
            String comment = resultSet.getString(COMMENT);
            Date dateCreateAt = new Date(resultSet.getLong(TIME_CREATED));
            String statusName = resultSet.getString(STATUS);
            Order.Status status = Order.Status.valueOf(statusName);
            BigDecimal totalPrice = resultSet.getBigDecimal(TOTAL_PRICE);

            Order order =new Order(id, comment, address, dateCreateAt, totalPrice, status);
            return order;
        } catch (SQLException exp) {
            throw new DaoException("Error while creating order from resultSet", exp);
        }
    }
}
