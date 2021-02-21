package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    List<Category> findAllCategories() throws DaoException;
    Optional<Category> findCategoryByName(String name) throws DaoException;
    Optional<Category> findCategoryById(long id) throws DaoException;
}
