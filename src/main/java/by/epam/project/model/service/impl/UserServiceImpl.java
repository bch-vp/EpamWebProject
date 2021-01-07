package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.impl.UserDaoImpl;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.validator.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.util.RequestParameterName.*;
import static by.epam.project.util.RequestParameterName.NOT_UNIQUE;


public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> signInUser(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        if (!UserValidator.isLoginCorrect(login) || !UserValidator.isPasswordCorrect(password)) {
            return Optional.empty();
        }

        try {
            Optional<User> foundUser = userDao.findByLoginAndPassword(login, password);

            return foundUser;
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign in user", exp);
        }
    }

    @Override
    public boolean signUpUser(User user, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isSignedUp;
        try {
            isSignedUp = userDao.add(user, password);
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign up user", exp);
        }
        return isSignedUp;
    }

    @Override
    public Map<String, String> defineSignUpData(String login, String password, String email, String name, String surname, String phone) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        Map<String, String> signUpData = UserValidator.validateParameters(login, password, email, name, surname, phone);
        try {
            signUpData.put(LOGIN_UNIQUE, userDao.findByLogin(login).isEmpty() ? login : NOT_UNIQUE);
            signUpData.put(EMAIL_UNIQUE, userDao.findByEmail(email).isEmpty() ? email : NOT_UNIQUE);
            signUpData.put(PHONE_UNIQUE, userDao.findByPhone(phone).isEmpty() ? phone : NOT_UNIQUE);
        } catch (DaoException exp) {
            throw new ServiceException("Error during define sign up data", exp);
        }
        return signUpData;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        return null;
    }

    @Override
    public List<User> sortByParameter(List<User> users, String sortType) throws ServiceException {
        return null;
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws ServiceException {
        return false;
    }
}