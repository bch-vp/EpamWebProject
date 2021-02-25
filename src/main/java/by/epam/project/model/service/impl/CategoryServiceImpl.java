package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.CategoryDao;
import by.epam.project.model.dao.impl.CategoryDaoImpl;
import by.epam.project.model.entity.Category;
import by.epam.project.model.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private static final CategoryServiceImpl instance = new CategoryServiceImpl();

    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    public static CategoryServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Category add(Category category) throws ServiceException {
        try {
            category = categoryDao.add(category);
        } catch (DaoException exp) {
            throw new ServiceException("Error during adding new category", exp);
        }

        return category;
    }

    @Override
    public List<Category> findAllCategories() throws ServiceException {
        List<Category> categories;

        try {
            categories = categoryDao.findAllCategories();
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all categories", exp);
        }

        return categories;
    }

    @Override
    public Optional<Category> findCategoryByName(String name) throws ServiceException {
        Optional<Category> categoryOptional;

        try {
            categoryOptional = categoryDao.findCategoryByName(name);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding category by name", exp);
        }

        return categoryOptional;
    }

    @Override
    public Optional<Category> findCategoryById(long id) throws ServiceException {
        Optional<Category> categoryOptional;

        try {
            categoryOptional = categoryDao.findCategoryById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding category by id", exp);
        }

        return categoryOptional;
    }

    @Override
    public boolean updateCategoryNameById(long id, String name) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = categoryDao.updateCategoryNameById(id, name);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating category name", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean removeCategoryById(long id) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = categoryDao.removeCategoryById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during removing category", exp);
        }

        return isUpdated;
    }
}
