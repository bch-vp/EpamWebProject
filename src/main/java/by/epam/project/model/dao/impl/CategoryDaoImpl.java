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
import java.util.Optional;

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

    @Override
    public Optional<Category> findCategoryByName(String name) throws DaoException {
        Optional<Category> categoryOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_CATEGORY_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoryOptional = Optional.of(ResultSetUtil.toCategory(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return categoryOptional;
    }

    @Override
    public Optional<Category> findCategoryById(long id) throws DaoException {
        Optional<Category> categoryOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_CATEGORY_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoryOptional = Optional.of(ResultSetUtil.toCategory(resultSet));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return categoryOptional;
    }

    @Override
    public boolean updateCategoryNameById(long id, String name) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_CATEGORY_NAME_BY_ID)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public boolean removeCategoryById(long id) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementUpdating =
                     connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_CATEGORY_BY_ID_TO_OTHERS);
             PreparedStatement statementRemoving = connection.prepareStatement(SqlQuery.REMOVE_CATEGORY_BY_ID)) {
            connection.setAutoCommit(false);
            statementUpdating.setLong(1, id);
            statementUpdating.executeUpdate();
            statementRemoving.setLong(1, id);
            isUpdated = statementRemoving.executeUpdate() == 1;
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }
}
