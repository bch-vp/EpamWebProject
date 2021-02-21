package by.epam.project.model.service;

import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories() throws ServiceException;
    Optional<Category> findCategoryByName(String name) throws ServiceException;
    Optional<Category> findCategoryById(long id) throws ServiceException;
}
