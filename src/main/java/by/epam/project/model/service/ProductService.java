package by.epam.project.model.service;

import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProductsByCategory(String category) throws ServiceException;
}
