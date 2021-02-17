package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAllProductsByCategory(String category) throws DaoException;
}
