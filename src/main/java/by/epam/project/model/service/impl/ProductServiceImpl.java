package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.ProductDao;
import by.epam.project.model.dao.impl.ProductDaoImpl;
import by.epam.project.model.entity.Order;
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
    public boolean add(Product product, long idCategory) throws ServiceException {
       boolean isUpdated;

        try {
            isUpdated = productDao.add(product, idCategory);
        } catch (DaoException exp) {
            throw new ServiceException("Error during adding new product", exp);
        }

        return isUpdated;
    }

    @Override
    public Optional<String> findImageURLByName(String name) throws ServiceException {
        Optional<String> stringOptional;

        try {
            stringOptional = productDao.findImageURLByName(name);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all products by category to client", exp);
        }

        return stringOptional;
    }

    @Override
    public boolean updateImageURLByName(String name, String fileURL) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = productDao.updateImageURLByName(name, fileURL);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating product image", exp);
        }

        return isUpdated;
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
    public boolean updateProductInfo(Product product) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = productDao.updateProductInfo(product);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating product info", exp);
        }

        return isUpdated;
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

    @Override
    public Optional<Product> findProductById(long id) throws ServiceException {
        Optional<Product> productOptional = Optional.empty();

        try {
            productOptional = productDao.findProductById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding product by id", exp);
        }

        return productOptional;
    }

    @Override
    public Optional<Product.Status> findStatusById(long id) throws ServiceException {
        Optional<Product.Status> statusOptional = Optional.empty();

        try {
            statusOptional = productDao.findStatusById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding product status", exp);
        }

        return statusOptional;
    }

    @Override
    public boolean updateProductCategory(long idProduct, long idCategory) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = productDao.updateProductCategory(idProduct, idCategory);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating product category", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean updateProductStatus(long idProduct, long idStatus) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = productDao.updateProductStatus(idProduct, idStatus);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating product status", exp);
        }

        return isUpdated;
    }

    @Override
    public List<Product> findAllOrderProducts(Order order) throws ServiceException {
        List<Product> products;

        try {
            products = productDao.findAllOrderProducts(order);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all products from order", exp);
        }

        return products;
    }
}
