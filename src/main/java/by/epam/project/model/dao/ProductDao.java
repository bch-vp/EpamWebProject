package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    boolean add(Product product, long idCategory) throws DaoException;

    Optional<String> findImageURLByName(String name) throws DaoException;

    boolean updateImageURLByName(String name, String fileURL) throws DaoException;

    List<Product> findAllProductsByCategoryToClient(String category) throws DaoException;

    List<Product> findAllProductsByCategoryToAdmin(String category) throws DaoException;

    Optional<Product> findProductByName(String name) throws DaoException;

    Optional<Product.Status> findStatusById(long id) throws DaoException;

    boolean updateProductInfo(Product product) throws DaoException;

    boolean updateProductCategory(long idProduct, long idCategory) throws DaoException;

    boolean updateProductStatus(long idProduct, long idStatus) throws DaoException;

    Optional<Product> findProductById(long id) throws DaoException;
}
