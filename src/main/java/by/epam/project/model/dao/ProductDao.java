package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAllProductsByCategoryToClient(String category) throws DaoException;
    Optional<Product> findProductByName(String name)  throws DaoException;

}
