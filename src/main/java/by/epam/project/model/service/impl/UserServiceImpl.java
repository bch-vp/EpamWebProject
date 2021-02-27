package by.epam.project.model.service.impl;

import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.impl.UserDaoImpl;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.util.EncryptPasswordUtil;
import by.epam.project.validator.ServiceValidator;
import org.apache.commons.fileupload.FileItem;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.*;
import static by.epam.project.controller.parameter.ParameterKey.NOT_UNIQUE;


public class UserServiceImpl implements by.epam.project.model.service.UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> signInUser(String login, String password) throws ServiceException {
        String encryptPassword = EncryptPasswordUtil.encryption(password);
        Optional<User> userOptional;

        try {
            userOptional = userDao.findByLoginAndPassword(login, encryptPassword);
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign in user", exp);
        }

        return userOptional;
    }

    @Override
    public boolean updateActivationStatusByLogin(String login, User.Status status) throws ServiceException {
        boolean isUpdated;

        if(!ServiceValidator.isLoginCorrect(login)){
            return false;
        }

        try {
            isUpdated = userDao.updateActivationStatusByLogin(login, status);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user's activation status", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean updateAvatarURLByLogin(String login, String fileURL) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = userDao.updateAvatarURLByLogin(login, fileURL);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user's url avatar", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean signUpUser(User user, String password) throws ServiceException {
        boolean isSignedUp;
        String encryptPassword = EncryptPasswordUtil.encryption(password);

        try {
            isSignedUp = userDao.add(user, encryptPassword);
        } catch (DaoException exp) {
            throw new ServiceException("Error during sign up user", exp);
        }

        return isSignedUp;
    }

    @Override
    public boolean updateUser(User newUser, String oldLogin) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = userDao.updateUser(newUser, oldLogin);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating info about user", exp);
        }

        return isUpdated;
    }

    @Override
    public Map<String, String> defineSignUpData(String login, String email, String firstName,
                                                String lastName, String phone) throws ServiceException {
        Map<String, String> signUpData =
                ServiceValidator.validateParameters(login, email, firstName, lastName, phone);

        try {
            signUpData.put(LOGIN_UNIQUE, userDao.findByLogin(login)
                    .isEmpty()
                    ? (login == null ? EMPTY_STRING : login)
                    : NOT_UNIQUE);
            signUpData.put(EMAIL_UNIQUE, userDao.findByEmail(email)
                    .isEmpty()
                    ? (email == null ? EMPTY_STRING : email)
                    : NOT_UNIQUE);
            signUpData.put(TELEPHONE_NUMBER_UNIQUE, userDao.findByTelephoneNumber(phone)
                    .isEmpty()
                    ? (phone == null ? EMPTY_STRING : phone)
                    : NOT_UNIQUE);
        } catch (DaoException exp) {
            throw new ServiceException("Error during define sign up data", exp);
        }

        return signUpData;
    }

    @Override
    public Map<String, String> defineSignUpData(String login, String password, String email, String firstName,
                                                String lastName, String phone) throws ServiceException {
        Map<String, String> signUpData =
                ServiceValidator.validateParameters(login, password, email, firstName, lastName, phone);

        try {
            signUpData.put(LOGIN_UNIQUE, userDao.findByLogin(login)
                    .isEmpty()
                    ? (login == null ? EMPTY_STRING : login)
                    : NOT_UNIQUE);
            signUpData.put(EMAIL_UNIQUE, userDao.findByEmail(email)
                    .isEmpty()
                    ? (email == null ? EMPTY_STRING : email)
                    : NOT_UNIQUE);
            signUpData.put(TELEPHONE_NUMBER_UNIQUE, userDao.findByTelephoneNumber(phone)
                    .isEmpty()
                    ? (phone == null ? EMPTY_STRING : phone)
                    : NOT_UNIQUE);
        } catch (DaoException exp) {
            throw new ServiceException("Error during define sign up data", exp);
        }

        return signUpData;
    }

    @Override
    public boolean removeAvatarByLogin(String login) throws ServiceException {
        boolean isDeleted;

        try {
            isDeleted = userDao.removeAvatarByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during remove user's avatar", exp);
        }

        return isDeleted;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
       List<User> users;

        try {
            users = userDao.findAllUsers();
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all users", exp);
        }

        return users;
    }

    @Override
    public List<User> findAllClients() throws ServiceException {
        List<User> users;

        try {
            users = userDao.findAllClients();
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all client", exp);
        }

        return users;
    }

    @Override
    public Optional<User> findUserById(long id) throws ServiceException {
        Optional<User> userOptional = Optional.empty();

        try {
            userOptional = userDao.findById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding user by id", exp);
        }

        return userOptional;
    }

    @Override
    public Optional<User.Status> findStatusById(long id) throws ServiceException {
        Optional<User.Status> statusOptional = Optional.empty();

        try {
            statusOptional = userDao.findStatusById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding user status", exp);
        }

        return statusOptional;
    }


    @Override
    public Optional<String> findAvatarURLByLogin(String login) throws ServiceException {
        Optional<String> stringOptional;

        try {
            stringOptional = userDao.findAvatarURLByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user's url avatar", exp);
        }

        return stringOptional;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> user;

        try {
            user = userDao.findByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding user by login", exp);
        }

        return user;
    }

    @Override
    public boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException {
        Optional<String> userPasswordOptional;

        try {
            userPasswordOptional = userDao.findPasswordByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking is password equal Login's password", exp);
        }

        return userPasswordOptional.isEmpty() || !EncryptPasswordUtil.encryption(password).equals(userPasswordOptional.get());
    }

    @Override
    public boolean updatePasswordByLogin(String login, String password) throws ServiceException {
        boolean isUpdated;
        String encryptPassword = EncryptPasswordUtil.encryption(password);

        try {
            isUpdated = userDao.updatePasswordByLogin(login, encryptPassword);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user password by login", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean isStatusEqualUserStatus(String login, User.Status status) throws ServiceException {
        Optional<User> userOptional;

        try {
            userOptional = userDao.findByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking user activated status", exp);
        }

        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return user.getStatus().equals(status);
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        boolean isUnique;
        Optional<User> userOptional;

        try {
            userOptional = userDao.findByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking is login unique", exp);
        }

        return userOptional.isEmpty();
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        boolean isUnique;
        Optional<User> userOptional;

        try {
            userOptional = userDao.findByEmail(email);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking is email unique", exp);
        }

        return userOptional.isEmpty();
    }

    @Override
    public boolean isTelephoneNumberUnique(String telephoneNumber) throws ServiceException {
        boolean isUnique;
        Optional<User> userOptional;

        try {
            userOptional = userDao.findByTelephoneNumber(telephoneNumber);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking is telephone number unique", exp);
        }

        return userOptional.isEmpty();
    }

    @Override
    public boolean updateUserStatus(long idUser, long idStatus) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updateUserStatus(idUser, idStatus);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating user status", exp);
        }

        return isUpdated;
    }

    @Override
    public boolean createOrder(User user,Order order, List<Product> products) throws ServiceException  {
        boolean isUpdated;

        BigDecimal totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);

        try {
            isUpdated = userDao.createOrder(user, order, products);
        } catch (DaoException exp) {
            throw new ServiceException("Error during creating order", exp);
        }

        return isUpdated;
    }

    @Override
    public List<Order> findAllOrders(User user) throws ServiceException {
        List<Order> orders;

        try {
            orders = userDao.findAllOrders(user);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all orders", exp);
        }

        return orders;
    }
}
