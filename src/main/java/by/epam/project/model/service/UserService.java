package by.epam.project.model.service;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> signInUser(String login, String password) throws ServiceException;
    boolean updateUser(User newUser, String oldLogin) throws ServiceException;
    boolean updateUserStatus(long idUser, long idStatus) throws ServiceException;
    Map<String, String> defineSignUpData(String login, String password, String email, String name,
                                         String surname, String phone) throws ServiceException;
    Map<String, String> defineSignUpData(String login, String email, String name,
                                         String surname, String phone) throws ServiceException;
    boolean signUpUser(User user, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    List<User> findAllClients() throws ServiceException;
    List<User> sortByParameter(List<User> users, String sortType) throws ServiceException;
    Optional<User.Status> findStatusById(long id) throws ServiceException;
    Optional<User> findUserById(long id) throws ServiceException;
    Optional<User> findUserByPhone(String phone) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByLogin(String login) throws ServiceException;
    Optional<String> findAvatarURLByLogin(String login) throws ServiceException;
    boolean updatePasswordByLogin(String login, String password) throws ServiceException;
    boolean updateAvatarURLByLogin(String login, String fileURL) throws ServiceException;
    boolean updateActivationStatusByLogin(String login, User.Status status) throws ServiceException;
    boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException;
    boolean isStatusEqualUserStatus(String login, User.Status status) throws ServiceException;
    boolean isLoginUnique(String login) throws ServiceException;
    boolean isEmailUnique(String email) throws ServiceException;
    boolean isTelephoneNumberUnique(String telephoneNumber) throws ServiceException;
    boolean removeAvatarByLogin(String login) throws ServiceException;
    boolean createOrder(User user, List<Product> products) throws ServiceException;
}
