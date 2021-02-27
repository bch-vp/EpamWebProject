package by.epam.project.validator;

import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.Order;
import by.epam.project.model.entity.Product;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.ProductService;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.ProductServiceImpl;
import by.epam.project.model.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ServiceValidator {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_.-]{3,15}+$";
    private static final String PASSWORD_REGEX= "^[A-Za-z0-9]{5,20}$";
    private static final String USER_NAME_REGEX = "^[a-zA-Z]{3,15}$";
    private static final String NAME_REGEX = "^.{3,15}$";
    private static final String ADDRESS_REGEX = "^.{3,50}$";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";
    private static final String EMAIL_REGEX = "^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\\.[a-z]{2,5}$";
    private static final String UNIQUE_KEY_REGEX = "^\\d{6}$";

    private static final String ID_REGEX = "^\\d{1,17}$";
    private static final String PRICE_REGEX = "^[0-9]{1,8}(\\.[0-9]{2})?$";
    private static final String INFO_REGEX = "^.{3,100}$";

    private ServiceValidator() {
    }

    public static void main(String[] args) throws JsonProcessingException {
        Order order1 = new Order("comm", "addr", new Date());
        Order order2 = new Order("comm2", "addr2", new Date());
        Order order3 = new Order("comm3", "addr2", new Date());
        List<Order> orders = Arrays.asList(order1, order2, order3);


        Product product1 = new Product("prod1","aegag", BigDecimal.ONE);
        Product product2 = new Product("prod2","aegag", BigDecimal.ONE);
        List<Product> products = Arrays.asList(product1, product2);


        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNodeOrders = objectMapper.valueToTree(orders);
        ArrayNode arrayNodeProducts = objectMapper.valueToTree(products);

        JsonNode firstOrder = arrayNodeOrders.path(1);

        ((ObjectNode)firstOrder).putArray("products").addAll(arrayNodeProducts);
        System.out.println(arrayNodeOrders.path(1));

//        ((ArrayNode)n).ad(key, value);


//        System.out.println(arrayNodeOrders.toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<Order> e = new ArrayList<Order>();
//        ArrayNode array = mapper.valueToTree(e);
//        ObjectNode companyNode = mapper.valueToTree(product1);
//        companyNode.putArray("Employee").addAll(array);
//        JsonNode result = mapper.createObjectNode().set("company", companyNode);

    }


    public static Map<String, String> validateParameters(String login, String password, String email,
                                                         String firstName, String lastName, String phone) {
        Map<String, String> validatedData = new HashMap<>();
        validatedData.put(LOGIN, isLoginCorrect(login) ? login : EMPTY_STRING);
        validatedData.put(PASSWORD, isPasswordCorrect(password) ? password : EMPTY_STRING);
        validatedData.put(FIRST_NAME, isFirstNameCorrect(firstName) ? firstName : EMPTY_STRING);
        validatedData.put(LAST_NAME, isLastNameCorrect(lastName) ? lastName : EMPTY_STRING);
        validatedData.put(TELEPHONE_NUMBER, isPhoneCorrect(phone) ? phone : EMPTY_STRING);
        validatedData.put(EMAIL, isEmailCorrect(email) ? email : EMPTY_STRING);
        return validatedData;
    }


    public static Map<String, String> validateParameters(String login, String email,
                                                         String firstName, String lastName, String phone) {
        Map<String, String> validatedData = new HashMap<>();
        validatedData.put(LOGIN, isLoginCorrect(login) ? login : EMPTY_STRING);
        validatedData.put(FIRST_NAME, isFirstNameCorrect(firstName) ? firstName : EMPTY_STRING);
        validatedData.put(LAST_NAME, isLastNameCorrect(lastName) ? lastName : EMPTY_STRING);
        validatedData.put(TELEPHONE_NUMBER, isPhoneCorrect(phone) ? phone : EMPTY_STRING);
        validatedData.put(EMAIL, isEmailCorrect(email) ? email : EMPTY_STRING);
        return validatedData;
    }

    public static boolean isLoginCorrect(String login) {
        return isEmptyOrNull(login) && isStringMatches(login, LOGIN_REGEX);
    }

    public static boolean isPasswordCorrect(String password) {
        return isEmptyOrNull(password) && isStringMatches(password, PASSWORD_REGEX);
    }

    public static boolean isEmailCorrect(String email) {
        return isEmptyOrNull(email) && isStringMatches(email, EMAIL_REGEX);
    }

    public static boolean isNameCorrect(String name) {
        return isEmptyOrNull(name) && isStringMatches(name, NAME_REGEX);
    }

    public static boolean isFirstNameCorrect(String name) {
        return isEmptyOrNull(name) && isStringMatches(name, USER_NAME_REGEX);
    }

    public static boolean isLastNameCorrect(String surname) {
        return isEmptyOrNull(surname) && isStringMatches(surname, USER_NAME_REGEX);
    }

    public static boolean isPhoneCorrect(String phone) {
        return isEmptyOrNull(phone) && isStringMatches(phone, PHONE_REGEX);
    }

    public static boolean isUniqueCodeCorrect(String uniqueCode) {
        return isEmptyOrNull(uniqueCode) && isStringMatches(uniqueCode, UNIQUE_KEY_REGEX);
    }

    public static boolean isIdCorrect(String id) {
        return isEmptyOrNull(id) && isStringMatches(id, ID_REGEX);
    }

    public static boolean isPriceCorrect(String price) {
        return isEmptyOrNull(price) && isStringMatches(price, PRICE_REGEX);
    }

    public static boolean isInfoCorrect(String info) {
        return isEmptyOrNull(info) && isStringMatches(info, INFO_REGEX);
    }

    public static boolean isAddressCorrect(String info) {
        return isEmptyOrNull(info) && isStringMatches(info, ADDRESS_REGEX);
    }

    public static boolean defineIncorrectValues(Map<String, String> data) {
        for (String key : data.keySet()) {
            if (data.get(key).isEmpty() || data.get(key).equals(NOT_UNIQUE)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEmptyOrNull(String string) {
        return string != null && !string.isEmpty();
    }

    private static boolean isStringMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
