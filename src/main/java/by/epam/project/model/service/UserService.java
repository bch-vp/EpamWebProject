package by.epam.project.model.service;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import org.apache.commons.fileupload.FileItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    AjaxData uploadUserImage(String userLogin, List<FileItem> fileItems, String language) throws ServiceException;
    AjaxData updateProfile(User user, String newLogin, String newFirstName, String newLastName,
                           String newTelephoneNumber, String newEmail, String language) throws ServiceException;
    AjaxData signIn(String login, String password, String language) throws ServiceException;
    AjaxData signUp(String login, String password, String firstName, String lastName, String telephoneNumber,
                    String email, String confirmationLink, String language) throws ServiceException;
    AjaxData changePasswordByOldPassword(User user, String oldPassword, String newPassword,
                                         String language) throws ServiceException;
    AjaxData changePasswordByEmail(String login, String newPassword, String email, String sessionUniqueKey,
                                   String requestUniqueKey, long timeCreated, String language) throws ServiceException;
    AjaxData findUserImage(String login) throws ServiceException;
    AjaxData removeUserImage(String login) throws ServiceException;
    AjaxData createOrder(User user, List<Product> shoppingCart, String orderAddress,
                          String orderComment) throws ServiceException;
    AjaxData findAllOrders(User user) throws ServiceException;
    AjaxData findAllClients() throws ServiceException;







    Optional<User> signInUser(String login, String password) throws ServiceException;

    boolean updateUser(User newUser, String oldLogin) throws ServiceException;

    boolean updateUserStatus(long idUser, long idStatus) throws ServiceException;

    boolean signUpUser(User user, String password) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;


    Optional<User.Status> findStatusById(long id) throws ServiceException;

    Optional<User> findUserById(long id) throws ServiceException;

    Optional<User> findUserByLogin(String login) throws ServiceException;

    Optional<User> findUserByOrderId(long id) throws ServiceException;

    Optional<String> findAvatarURLByLogin(String login) throws ServiceException;

    boolean updatePasswordByLogin(String login, String password) throws ServiceException;

    boolean updateAvatarURLByLogin(String login, String fileURL) throws ServiceException;

    boolean updateActivationStatusByLogin(String login, User.Status status) throws ServiceException;

    boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException;

    boolean isStatusEqualUserStatus(String login, User.Status status) throws ServiceException;

    boolean removeAvatarByLogin(String login) throws ServiceException;

    boolean createOrder(User user, Order order, List<Product> products) throws ServiceException;

    List<Order> findAllOrdersToClient(User user) throws ServiceException;

    List<Order> findAllOrdersToAdmin() throws ServiceException;

    Optional<Order> findOrderById(long id) throws ServiceException;

    boolean updateOrderStatusById(long idOrder, long idStatus) throws ServiceException;

    Optional<Order.Status> findOrderStatusById(long id) throws ServiceException;
}
