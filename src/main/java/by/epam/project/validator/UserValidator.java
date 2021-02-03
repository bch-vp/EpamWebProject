package by.epam.project.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.project.controller.parameter.RequestParameterKey.*;

public class UserValidator {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_.-]{3,15}+$";
    private static final String PASSWORD_REGEX= "^[A-Za-z0-9]{5,20}$";
    private static final String FIRST_NAME_REGEX = "^[a-zA-Z]{3,15}$";
    private static final String LAST_NAME_REGEX = "^[a-zA-Z]{3,15}$";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";
    private static final String EMAIL_REGEX = "^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\\.[a-z]{2,5}$";
    private static final String UNIQUE_KEY_REGEX = "^\\d{6}$";

    private UserValidator() {
    }

    public static Map<String, String> validateParameters(String login, String password, String email,
                                                         String firstName, String lastName, String phone) {
        Map<String, String> validatedData = new HashMap<>();
        validatedData.put(USER_LOGIN, isLoginCorrect(login) ? login : EMPTY_STRING);
        validatedData.put(USER_PASSWORD, isPasswordCorrect(password) ? password : EMPTY_STRING);
        validatedData.put(USER_EMAIL, isEmailCorrect(email) ? email : EMPTY_STRING);
        validatedData.put(USER_NAME, isFirstNameCorrect(firstName) ? firstName : EMPTY_STRING);
        validatedData.put(USER_SURNAME, isSecondNameCorrect(lastName) ? lastName : EMPTY_STRING);
        validatedData.put(USER_PHONE, isPhoneCorrect(phone) ? phone : EMPTY_STRING);
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

    public static boolean isFirstNameCorrect(String name) {
        return isEmptyOrNull(name) && isStringMatches(name, FIRST_NAME_REGEX);
    }

    public static boolean isSecondNameCorrect(String surname) {
        return isEmptyOrNull(surname) && isStringMatches(surname, LAST_NAME_REGEX);
    }

    public static boolean isPhoneCorrect(String phone) {
        return isEmptyOrNull(phone) && isStringMatches(phone, PHONE_REGEX);
    }

    public static boolean isUniqueCodeCorrect(String uniqueCode) {
        return isEmptyOrNull(uniqueCode) && isStringMatches(uniqueCode, UNIQUE_KEY_REGEX);
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
