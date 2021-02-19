package by.epam.project.model.service;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProductsByCategoryToClient(String category) throws ServiceException;
    List<Product> findAllProductsByCategoryToAdmin(String category) throws ServiceException;
    Optional<Product> findProductByName(String name)  throws ServiceException;
}
