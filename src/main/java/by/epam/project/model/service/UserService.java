package by.epam.project.model.service;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> signInUser(String login, String password) throws ServiceException;
    boolean updateUser(User newUser, String oldLogin) throws ServiceException;
    Map<String, String> defineSignUpData(String login, String password, String email, String name,
                                         String surname, String phone) throws ServiceException;
    Map<String, String> defineSignUpData(String login, String email, String name,
                                         String surname, String phone) throws ServiceException;
    boolean signUpUser(User user, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    List<User> sortByParameter(List<User> users, String sortType) throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    Optional<User> findUserByPhone(String phone) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByLogin(String login) throws ServiceException;
    boolean updatePasswordByLogin(String login, String password) throws ServiceException;
    boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException;
    boolean isActivated(String login) throws ServiceException;
}
