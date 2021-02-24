package by.epam.project.model.dao.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.ProductDao;
import by.epam.project.model.dao.SqlQuery;
import by.epam.project.model.entity.Product;
import by.epam.project.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.IMAGE_URL;
import static by.epam.project.controller.parameter.ParameterKey.NAME;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Product product, long idCategory) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getInfo());
            statement.setBigDecimal(3, product.getPrice());
            statement.setLong(4, idCategory);
            statement.setLong(5, product.getStatus().ordinal() + 1);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public Optional<String> findImageURLByName(String name) throws DaoException {
        Optional<String> stringOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PRODUCT_IMAGE_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                stringOptional = Optional.ofNullable(resultSet.getString(IMAGE_URL));
            }
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return stringOptional;
    }

    @Override
    public boolean updateImageURLByName(String name, String fileURL) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_IMAGE_URL)) {
            statement.setString(1, fileURL);
            statement.setString(2, name);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public List<Product> findAllProductsByCategoryToClient(String category) throws DaoException {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_PRODUCTS_BY_CATEGORY_TO_CLIENT)) {
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

    @Override
    public boolean updateProductInfo(Product product) throws DaoException {
       boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_INFO)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getInfo());
            statement.setBigDecimal(3, product.getPrice());
            statement.setLong(4, product.getId());
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public boolean updateProductCategory(long idProduct, long idCategory) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_CATEGORY)) {
            statement.setLong(1, idCategory);
            statement.setLong(2, idProduct);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public boolean updateProductStatus(long idProduct, long idStatus) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT_STATUS)) {
            statement.setLong(1, idStatus);
            statement.setLong(2, idProduct);
            isUpdated = statement.executeUpdate() == 1;
        } catch (SQLException exp) {
            throw new DaoException(exp);
        }

        return isUpdated;
    }

    @Override
    public List<Product> findAllProductsByCategoryToAdmin(String category) throws DaoException {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_PRODUCTS_BY_CATEGORY_TO_ADMIN)) {
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

    @Override
    public Optional<Product> findProductByName(String name) throws DaoException {
        Optional<Product> productOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PRODUCT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = ResultSetUtil.toProduct(resultSet);
                productOptional = Optional.of(product);
            }

        } catch (
                SQLException exp) {
            throw new DaoException(exp);
        }

        return productOptional;
    }

    @Override
    public Optional<Product> findProductById(long id) throws DaoException {
        Optional<Product> productOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = ResultSetUtil.toProduct(resultSet);
                productOptional = Optional.of(product);
            }

        } catch (
                SQLException exp) {
            throw new DaoException(exp);
        }

        return productOptional;
    }

    @Override
    public Optional<Product.Status> findStatusById(long id) throws DaoException {
        Optional<Product.Status> statusOptional = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PRODUCT_STATUS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product.Status status = Product.Status.valueOf(resultSet.getString(NAME));
                statusOptional = Optional.of(status);
            }

        } catch (
                SQLException exp) {
            throw new DaoException(exp);
        }


        return statusOptional;
    }
}
