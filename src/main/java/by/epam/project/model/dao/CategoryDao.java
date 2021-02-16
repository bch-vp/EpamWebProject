package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.model.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategories() throws DaoException;
}
