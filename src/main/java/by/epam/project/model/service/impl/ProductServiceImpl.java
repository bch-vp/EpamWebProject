package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.ProductDao;
import by.epam.project.model.dao.impl.ProductDaoImpl;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final ProductServiceImpl instance = new ProductServiceImpl();

    private final ProductDao productDao = ProductDaoImpl.getInstance();

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Product> findAllProductsByCategoryToClient(String category) throws ServiceException {
        List<Product> products;

        try {
            products = productDao.findAllProductsByCategoryToClient(category);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all products by category to client", exp);
        }

        return products;
    }

    @Override
    public List<Product> findAllProductsByCategoryToAdmin(String category) throws ServiceException {
        List<Product> products;

        try {
            products = productDao.findAllProductsByCategoryToAdmin(category);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all products by category to admin", exp);
        }

        return products;
    }

    @Override
    public Optional<Product> findProductByName(String name) throws ServiceException {
        Optional<Product> productOptional = Optional.empty();

        try {
            productOptional = productDao.findProductByName(name);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding product by name", exp);
        }

        return productOptional;
    }
}
