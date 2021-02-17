package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.ProductDao;
import by.epam.project.model.dao.impl.CategoryDaoImpl;
import by.epam.project.model.dao.impl.ProductDaoImpl;
import by.epam.project.model.entity.Category;
import by.epam.project.model.entity.Product;
import by.epam.project.model.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final ProductServiceImpl instance = new ProductServiceImpl();

    private final ProductDao productDao = ProductDaoImpl.getInstance();

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Product> findAllProductsByCategory(String category) throws ServiceException {
        List<Product> products;

        try {
            products = productDao.findAllProductsByCategory(category);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all products by category", exp);
        }

        return products;
    }
}
