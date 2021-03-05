package by.epam.project.model.service;

import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category add(Category category) throws ServiceException;

    List<Category> findAllCategories() throws ServiceException;

    Optional<Category> findCategoryByName(String name) throws ServiceException;

    Optional<Category> findCategoryById(long id) throws ServiceException;

    boolean updateCategoryNameById(long id, String name) throws ServiceException;

    boolean removeCategoryById(long id) throws ServiceException;
}
