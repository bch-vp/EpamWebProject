package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.impl.UserDaoImpl;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.util.EncryptPassword;
import by.epam.project.validator.UserValidator;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;
import static by.epam.project.controller.parameter.ParameterKey.NOT_UNIQUE;


public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> signInUser(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        String encryptPassword = EncryptPassword.encryption(password);
        try {
            Optional<User> foundUser = userDao.findByLoginAndPassword(login, encryptPassword);
            return foundUser;
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign in user", exp);
        }
    }

    @Override
    public boolean updateAvatarByLogin(String login, InputStream inputStream) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isUpdated;
        try {
            isUpdated = userDao.updateAvatarByLogin(login, inputStream);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating avatar", exp);
        }
        return isUpdated;
    }

    @Override
    public boolean signUpUser(User user, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isSignedUp;
        String encryptPassword = EncryptPassword.encryption(password);
        try {
            isSignedUp = userDao.add(user, encryptPassword);
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign up user", exp);
        }
        return isSignedUp;
    }

    @Override
    public Map<String, String> defineSignUpData(String login, String password, String email, String firstName,
                                                String lastName, String phone) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        Map<String, String> signUpData =
                UserValidator.validateParameters(login, password, email, firstName, lastName, phone);
        try {
            signUpData.put(LOGIN_UNIQUE, userDao.findByLogin(login)
                    .isEmpty()
                    ? (login == null ? EMPTY_STRING : login)
                    : NOT_UNIQUE);
            signUpData.put(EMAIL_UNIQUE, userDao.findByEmail(email)
                    .isEmpty()
                    ? (email == null ? EMPTY_STRING : email)
                    : NOT_UNIQUE);
            signUpData.put(TELEPHONE_NUMBER_UNIQUE, userDao.findByPhone(phone)
                    .isEmpty()
                    ? (phone == null ? EMPTY_STRING : phone)
                    : NOT_UNIQUE);
        } catch (DaoException exp) {
            throw new ServiceException("Error during define sign up data", exp);
        }
        return signUpData;
    }

    @Override
    public boolean updateUser(User newUser, String oldLogin) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isUpdated;

        try {
            isUpdated = userDao.updateUser(newUser, oldLogin);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user", exp);
        }
        return isUpdated;
    }

    @Override
    public Map<String, String> defineSignUpData(String login, String email, String firstName,
                                                String lastName, String phone) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        Map<String, String> signUpData =
                UserValidator.validateParameters(login, email, firstName, lastName, phone);
        try {
            signUpData.put(LOGIN_UNIQUE, userDao.findByLogin(login)
                    .isEmpty()
                    ? (login == null ? EMPTY_STRING : login)
                    : NOT_UNIQUE);
            signUpData.put(EMAIL_UNIQUE, userDao.findByEmail(email)
                    .isEmpty()
                    ? (email == null ? EMPTY_STRING : email)
                    : NOT_UNIQUE);
            signUpData.put(TELEPHONE_NUMBER_UNIQUE, userDao.findByPhone(phone)
                    .isEmpty()
                    ? (phone == null ? EMPTY_STRING : phone)
                    : NOT_UNIQUE);
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
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Optional<User> user;
        try {
            user = userDao.findByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during define data", exp);
        }
        return user;
    }

    @Override
    public boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            Optional<String> userPassword = userDao.findPasswordByLogin(login);
            if (userPassword.isEmpty() || !EncryptPassword.encryption(password).equals(userPassword.get())) {
                return false;
            }else {
                return true;
            }
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign in user", exp);
        }
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isUpdated;
        String encryptPassword = EncryptPassword.encryption(password);
        try {
            isUpdated = userDao.updatePasswordByLogin(login, encryptPassword);
            return isUpdated;
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign in user", exp);
        }
    }

    @Override
    public boolean isActivated(String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean isActivated;
        try {
            Optional<User> userOptional = userDao.findByLogin(login);
            if(userOptional.isEmpty()){
                return false;
            }
            User user = userOptional.get();
            return user.isActivated();
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking user activated status", exp);
        }
    }
}
