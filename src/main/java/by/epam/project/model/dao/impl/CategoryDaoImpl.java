package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.CategoryDao;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.entity.Category;
import by.epam.project.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    public static CategoryDaoImpl getInstance() {
        return instance;
    }

    private CategoryDaoImpl(){}

    @Override
    public List<Category> findAllCategories() throws DaoException {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CATEGORIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = ResultSetUtil.toCategory(resultSet);
                categories.add(category);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return categories;
    }
}
