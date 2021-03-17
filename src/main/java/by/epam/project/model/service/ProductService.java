package by.epam.project.model.service;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface ProductService {
    AjaxData addProductToShoppingCart(List<Product> shoppingCart, String productName) throws ServiceException;

    AjaxData findAllProductsByCategory(User.Role userRole, String categoryName,
                                       List<Product> shoppingCart) throws ServiceException;

    AjaxData removeProductFromShoppingCart(List<Product> shoppingCart, String productName) throws ServiceException;

    AjaxData loadShoppingCart(List<Product> shoppingCart) throws ServiceException;

    AjaxData updateProductCategory(String idProductString, String idCategoryString) throws ServiceException;

    AjaxData updateProductInfo(String idString, String name,
                               String info, String priceString) throws ServiceException;

    AjaxData updateProductStatus(String idProductString, String idStatusString) throws ServiceException;

    AjaxData uploadProductImage(String productName, List<FileItem> fileItems, String language) throws ServiceException;

    AjaxData createProduct(String idCategoryString, String name, String info,
                           String priceString, String language) throws ServiceException;
}
