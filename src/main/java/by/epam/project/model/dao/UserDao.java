package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add(User user, String password) throws DaoException;

    boolean updatePasswordByLogin(String login, String password) throws DaoException;

    boolean updateUser(User newUser, String oldLogin) throws DaoException;

    boolean updateAvatarURLByLogin(String login, String fileURL) throws DaoException;

    boolean updateActivationStatusByLogin(String login, User.Status status) throws DaoException;

    boolean updateUserStatus(long idUser, long idStatus) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    List<User> findAllClients() throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<User> findByTelephoneNumber(String phone) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User.Status> findStatusById(long id) throws DaoException;

    Optional<String> findAvatarURLByLogin(String login) throws DaoException;

    Optional<String> findPasswordByLogin(String login) throws DaoException;

    Optional<User> findByLoginAndPassword(String login, String password) throws DaoException;

    boolean removeAvatarByLogin(String login) throws DaoException;

    Optional<User> findById(long id) throws DaoException;

    boolean createOrder(User user, Order order, List<Product> products) throws DaoException;

    List<Order> findAllOrders(User user) throws DaoException;
}
