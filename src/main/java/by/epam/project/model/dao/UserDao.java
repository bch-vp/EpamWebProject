package by.epam.project.model.dao;

import by.epam.project.exception.DaoException;
import by.epam.project.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User>{
    boolean add(User user, String password) throws DaoException;
    boolean updatePasswordByLogin(String login, String password) throws DaoException;
    Optional<User> findById(int id) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    Optional<User> findByPhone(String phone) throws DaoException;
    String findPasswordByLogin(String login) throws DaoException;
    boolean activateUser(String login) throws DaoException;
    Optional<User> findByLoginAndPassword(String login, String password) throws DaoException;
}
