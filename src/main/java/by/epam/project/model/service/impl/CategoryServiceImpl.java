package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.CategoryDao;
import by.epam.project.model.dao.impl.CategoryDaoImpl;
import by.epam.project.model.entity.Category;
import by.epam.project.model.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final CategoryServiceImpl instance = new CategoryServiceImpl();

    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    public static CategoryServiceImpl getInstance() {
        return instance;
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
}
