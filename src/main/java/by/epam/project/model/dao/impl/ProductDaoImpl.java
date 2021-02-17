package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.ProductDao;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.entity.Category;
import by.epam.project.model.entity.Product;
import by.epam.project.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Product> findAllProductsByCategory(String category) throws DaoException {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_PRODUCTS_BY_CATEGORY)) {
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = ResultSetUtil.toProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return products;
    }
}
