package by.epam.project.model.service;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    AjaxData createCategory(String nameCategory, String language) throws ServiceException;
    AjaxData findAllCategories() throws ServiceException;
    AjaxData removeCategory(String idCategoryString) throws ServiceException;
    AjaxData updateCategoryName(String idCategoryString, String newName, String language) throws ServiceException;
}
