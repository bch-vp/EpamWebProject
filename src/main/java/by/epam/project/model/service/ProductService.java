package by.epam.project.model.service;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProductsByCategoryToClient(String category) throws ServiceException;

    List<Product> findAllProductsByCategoryToAdmin(String category) throws ServiceException;

    Optional<Product> findProductByName(String name) throws ServiceException;

    Optional<Product> findProductById(long id) throws ServiceException;

    Optional<Product.Status> findStatusById(long id) throws ServiceException;

    boolean updateProductInfo(Product product) throws ServiceException;

    boolean updateProductStatus(long idProduct, long idStatus) throws ServiceException;

    boolean updateProductCategory(long idProduct, long idCategory) throws ServiceException;
}
