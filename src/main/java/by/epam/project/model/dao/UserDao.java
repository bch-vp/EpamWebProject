package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserDao{
    boolean add(User user, String password) throws DaoException;
    boolean updatePasswordByLogin(String login, String password) throws DaoException;
    boolean updateUser(User newUser, String oldLogin) throws DaoException;
    boolean updateAvatarByLogin(String login, InputStream inputStream) throws DaoException;
    boolean updateActivationStatusByLogin(String login, User.Status status) throws DaoException;
    boolean updateUserStatus(long idUser, long idStatus) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    List<User> findAllClients() throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> findByTelephoneNumber(String phone) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    Optional<User.Status> findStatusById(long id) throws DaoException;
    Optional<byte[]> findAvatarByLogin(String login) throws DaoException;
    Optional<String> findPasswordByLogin(String login) throws DaoException;
    boolean activateUser(String login) throws DaoException;
    Optional<User> findByLoginAndPassword(String login, String password) throws DaoException;
    boolean removeAvatarByLogin(String login) throws DaoException;
    Optional<User> findById(long id) throws DaoException;
}
