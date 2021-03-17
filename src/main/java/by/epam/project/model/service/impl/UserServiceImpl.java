package by.epam.project.model.service.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.parameter.ContentKey;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.dao.impl.UserDaoImpl;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.util.*;
import by.epam.project.validator.ServiceValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static by.epam.project.controller.parameter.ContentKey.*;
import static by.epam.project.controller.parameter.ErrorKey.*;
import static by.epam.project.controller.parameter.ParameterKey.*;


public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    static final int FILE_MAX_SIZE = 1024 * 1024 * 2;
    private static final String FILE_TYPE = "image/jpg, image/png, image/jpeg";
    private static final int FILES_COUNT = 1;
    private static final int FIRST = 0;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public AjaxData uploadUserImage(String userLogin, List<FileItem> fileItems, String language) throws ServiceException {
        AjaxData ajaxData = new AjaxData();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        if (!ServiceValidator.isNameCorrect(userLogin)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        try {
            if (fileItems.size() != FILES_COUNT) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_PROFILE_AVATAR_COUNT_ALLOWED_FILES, language);
                return ajaxData;
            }

            FileItem file = fileItems.get(FIRST);
            if (file.getSize() > FILE_MAX_SIZE) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_PROFILE_AVATAR_MAX_SIZE, language);
                return ajaxData;
            }

            if (file.isFormField()) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_PROFILE_AVATAR_FORM_FIELD, language);
                return ajaxData;
            }

            String contentType = file.getContentType();
            if (!FILE_TYPE.contains(contentType)) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_PROFILE_AVATAR_FORMAT, language);
                return ajaxData;
            }

            Optional<String> URLOptional = userDao.findAvatarURLByLogin(userLogin);
            if (URLOptional.isPresent()) {
                String avatarURL = URLOptional.get();
                FileUtil.remove(avatarURL);
            }

            String fileURL = FileUtil.save(file);
            userDao.updateAvatarURLByLogin(userLogin, fileURL);
            JsonUtil.writeJsonToAjaxData(ajaxData, URL, fileURL);
        } catch (DaoException | IOException exp) {
            throw new ServiceException(exp);
        }

        return ajaxData;
    }

    @Override
    public AjaxData updateProfile(User user, String newLogin, String newFirstName, String newLastName,
                                  String newTelephoneNumber, String newEmail, String language) throws ServiceException {
        AjaxData ajaxData = new AjaxData();

        if (!ServiceValidator.isLoginCorrect(newLogin)
                || !ServiceValidator.isFirstNameCorrect(newFirstName)
                || !ServiceValidator.isLastNameCorrect(newLastName)
                || !ServiceValidator.isPhoneCorrect(newTelephoneNumber)
                || !ServiceValidator.isEmailCorrect(newEmail)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        JsonNode jsonTree = JsonUtil.createJsonTree(ERROR);
        try {
            if (userDao.findByLogin(newLogin).isPresent() && !user.getLogin().equals(newLogin)) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, LOGIN_NOT_UNIQUE, error, ERROR);
            }
            if (userDao.findByTelephoneNumber(newTelephoneNumber).isPresent() &&
                    !user.getTelephoneNumber().equals(newTelephoneNumber)) {
                String error = ContentUtil.getWithLocale(language,
                        ContentKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.TELEPHONE_NUMBER_NOT_UNIQUE, error, ERROR);
            }
            if (userDao.findByEmail(newEmail).isPresent() && !user.getEmail().equals(newEmail)) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, EMAIL_NOT_UNIQUE, error, ERROR);
            }

            if (!jsonTree.path(ERROR).isEmpty()) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonTreeToResponse(ajaxData, jsonTree);
                return ajaxData;
            }

            User newUser = new User(user.getId(), newLogin, newFirstName, newLastName, newTelephoneNumber,
                    newEmail, user.getRole(), user.getStatus());
            userDao.updateUser(newUser, user.getLogin());
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }

        return ajaxData;
    }

    @Override
    public AjaxData signIn(String login, String password, String language) throws ServiceException {
        AjaxData ajaxData = new AjaxData();

        if (!ServiceValidator.isLoginCorrect(login)
                || !ServiceValidator.isPasswordCorrect(password)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        String encryptPassword = EncryptPasswordUtil.encryption(password);

        Optional<User> userOptional;
        try {
            userOptional = userDao.findByLoginAndPassword(login, encryptPassword);


            if (userOptional.isEmpty()) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_NOT_FOUND);
                return ajaxData;
            }

            User user = userOptional.get();
            if (user.getStatus().equals(User.Status.BANNED)) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_SIGN_IN_BANNED, language);
                return ajaxData;
            }

            if (!user.getStatus().equals(User.Status.ACTIVATED)) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR, ERROR_SIGN_IN_NOT_ACTIVATED, language);
                return ajaxData;
            }

            User.Role role = user.getRole();
            if (role == User.Role.CLIENT) {
                JsonUtil.writeJsonToAjaxData(ajaxData, INFO, PASSING_BY_CLIENT);
            } else {
                JsonUtil.writeJsonToAjaxData(ajaxData, INFO, PASSING_BY_ADMIN);
            }
            ajaxData.putDataToDataSession(USER, user);
        } catch (DaoException | IOException exp) {
            throw new ServiceException(exp);
        }

        return ajaxData;
    }

    @Override
    public AjaxData signUp(String login, String password, String firstName, String lastName, String telephoneNumber,
                           String email, String confirmationLink, String language) throws ServiceException {
        AjaxData ajaxData = new AjaxData();

        if (!ServiceValidator.isLoginCorrect(login)
                || !ServiceValidator.isPasswordCorrect(password)
                || !ServiceValidator.isFirstNameCorrect(firstName)
                || !ServiceValidator.isLastNameCorrect(lastName)
                || !ServiceValidator.isPhoneCorrect(telephoneNumber)
                || !ServiceValidator.isEmailCorrect(email)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        JsonNode jsonTree = JsonUtil.createJsonTree(ERROR);
        try {
            if (userDao.findByLogin(login).isPresent()) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_LOGIN_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, LOGIN_NOT_UNIQUE, error, ERROR);
            }
            if (userDao.findByTelephoneNumber(telephoneNumber).isPresent()) {
                String error = ContentUtil.getWithLocale(language,
                        ContentKey.ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, ErrorKey.TELEPHONE_NUMBER_NOT_UNIQUE, error, ERROR);
            }
            if (userDao.findByEmail(email).isPresent()) {
                String error = ContentUtil.getWithLocale(language, ContentKey.ERROR_SIGN_UP_EMAIL_NOT_UNIQUE);
                JsonUtil.addNodeToJsonTree(jsonTree, EMAIL_NOT_UNIQUE, error, ERROR);
            }
            if (!jsonTree.path(ERROR).isEmpty()) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonTreeToResponse(ajaxData, jsonTree);
                return ajaxData;
            }

            User user = new User(login, firstName, lastName, telephoneNumber, email,
                    User.Role.CLIENT, User.Status.NOT_ACTIVATED);
            String encryptPassword = EncryptPasswordUtil.encryption(password);
            userDao.add(user, encryptPassword);

            String emailSubjectWithLocale = ContentUtil.getWithLocale(language,
                    ContentKey.EMAIL_SUBJECT_ACTIVATION_SIGN_UP);
            String emailBodyWithLocale = ContentUtil.getWithLocale(language,
                    ContentKey.EMAIL_BODY_ACTIVATION_SIGN_UP);

            String command = CommandType.CONFIRM_SIGN_UP.toString().toLowerCase();
            MailSenderUtil.sendActivationEmail(user, emailSubjectWithLocale,
                    emailBodyWithLocale, confirmationLink, command);

            ajaxData.setStatusHttp(HttpServletResponse.SC_CREATED);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }

        return ajaxData;
    }

    @Override
    public AjaxData changePasswordByOldPassword(User user, String oldPassword, String newPassword,
                                                String language) throws ServiceException {
        AjaxData ajaxData = new AjaxData();

        if (ServiceValidator.isPasswordCorrect(oldPassword)
                && ServiceValidator.isPasswordCorrect(newPassword)
                && oldPassword.equals(newPassword)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        String encryptOldPassword = EncryptPasswordUtil.encryption(newPassword);
        try {
            Optional<String> userPasswordOptional = userDao.findPasswordByLogin(user.getLogin());
            if(userPasswordOptional.isPresent() && !encryptOldPassword.equals(userPasswordOptional.get())){
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData,
                        ERROR, ERROR_PROFILE_OLD_PASSWORD_NOT_EQUAL_LOGIN_PASSWORD, language);
                return;
            }


            boolean isEqual = isPasswordEqualLoginPassword(user.getLogin(), oldPassword);
            if (!isEqual) {
                ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToAjaxData(ajaxData, ERROR,
                        ERROR_PROFILE_OLD_PASSWORD_NOT_EQUAL_LOGIN_PASSWORD, language);
                return ajaxData;
            }
            userDao.updatePasswordByLogin(user.getLogin(), newPassword);
        } catch (DaoException | IOException exp) {
            throw new ServiceException(exp);
        }

        return ajaxData;
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

        if (!ServiceValidator.isLoginCorrect(login)) {
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
    public Optional<User> findUserByOrderId(long id) throws ServiceException {
        Optional<User> userOptional = Optional.empty();

        try {
            userOptional = userDao.findUserByOrderId(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding user by order id", exp);
        }

        return userOptional;
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
    private boolean isPasswordEqualLoginPassword(String login, String password) throws ServiceException {
        Optional<String> userPasswordOptional;

        try {
            userPasswordOptional = userDao.findPasswordByLogin(login);
        } catch (DaoException exp) {
            throw new ServiceException("Error during checking is password equal login's password", exp);
        }

        return userPasswordOptional.isPresent()
                && !EncryptPasswordUtil.encryption(password).equals(userPasswordOptional.get());
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
//
//    @Override
//    private boolean isLoginUnique(String login) throws ServiceException {
//        boolean isUnique;
//        Optional<User> userOptional;
//
//        try {
//            userOptional = userDao.findByLogin(login);
//        } catch (DaoException exp) {
//            throw new ServiceException("Error during checking is login unique", exp);
//        }
//
//        return userOptional.isEmpty();
//    }
//
//    @Override
//    private boolean isEmailUnique(String email) throws ServiceException {
//        boolean isUnique;
//        Optional<User> userOptional;
//
//        try {
//            userOptional = userDao.findByEmail(email);
//        } catch (DaoException exp) {
//            throw new ServiceException("Error during checking is email unique", exp);
//        }
//
//        return userOptional.isEmpty();
//    }
//
//    @Override
//    private boolean isTelephoneNumberUnique(String telephoneNumber) throws ServiceException {
//        boolean isUnique;
//        Optional<User> userOptional;
//
//        try {
//            userOptional = userDao.findByTelephoneNumber(telephoneNumber);
//        } catch (DaoException exp) {
//            throw new ServiceException("Error during checking is telephone number unique", exp);
//        }
//
//        return userOptional.isEmpty();
//    }

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
    public boolean createOrder(User user, Order order, List<Product> products) throws ServiceException {
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
    public List<Order> findAllOrdersToClient(User user) throws ServiceException {
        List<Order> orders;

        try {
            orders = userDao.findAllOrdersToClient(user);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all orders to client", exp);
        }

        return orders;
    }

    @Override
    public List<Order> findAllOrdersToAdmin() throws ServiceException {
        List<Order> orders;

        try {
            orders = userDao.findAllOrdersToAdmin();
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding all orders to admin", exp);
        }

        return orders;
    }

    @Override
    public Optional<Order> findOrderById(long id) throws ServiceException {
        Optional<Order> order;

        try {
            order = userDao.findOrderById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding order by id", exp);
        }

        return order;
    }

    @Override
    public boolean updateOrderStatusById(long idOrder, long idStatus) throws ServiceException {
        boolean isUpdated;

        try {
            isUpdated = userDao.updateOrderStatusById(idOrder, idStatus);
        } catch (DaoException exp) {
            throw new ServiceException("Error during updating order status", exp);
        }

        return isUpdated;
    }

    @Override
    public Optional<Order.Status> findOrderStatusById(long id) throws ServiceException {
        Optional<Order.Status> status;

        try {
            status = userDao.findOrderStatusById(id);
        } catch (DaoException exp) {
            throw new ServiceException("Error during finding order status by id", exp);
        }

        return status;
    }
}
