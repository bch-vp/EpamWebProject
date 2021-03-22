package by.epam.project.validator;

import by.epam.project.controller.async.command.CommandType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ServiceValidator {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_.-]{3,15}$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]{5,20}$";
    private static final String PASSWORD_REGEX_ONE_DIGIT = "(?=.*?[0-9])";
    private static final String PASSWORD_REGEX_ONE_LOWER_CASE_LETTER = "(?=.*?[a-z])";
    private static final String PASSWORD_REGEX_ONE_UPPER_CASE_LETTER = "(?=.*?[A-Z])";
    private static final String USER_NAME_REGEX = "^[a-zA-Z]{3,15}$";
    private static final String NAME_REGEX = "^.{3,15}$";
    private static final String ADDRESS_REGEX = "^.{3,50}$";
    private static final String PHONE_REGEX = "^(\\+375\\([\\d]{2}\\)[\\d]{3}\\-[\\d]{2}\\-[\\d]{2})$";
    private static final String EMAIL_REGEX = "^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\\.[a-z]{2,5}$";
    private static final String UNIQUE_KEY_REGEX = "^\\d{6}$";

    private static final String ID_REGEX = "^\\d{1,17}$";
    private static final String PRICE_REGEX = "^[0-9]{1,8}(\\.[0-9]{2})?$";
    private static final String INFO_REGEX = "^.{3,100}$";

    private ServiceValidator() {}

    public static boolean isLoginCorrect(String login) {
        return isNotEmptyOrNull(login) && isStringMatches(login, LOGIN_REGEX);
    }

    public static boolean isPasswordCorrect(String password) {
        return isNotEmptyOrNull(password)
                && isStringMatches(password, PASSWORD_REGEX)
                && isStringContain(password, PASSWORD_REGEX_ONE_DIGIT)
                && isStringContain(password, PASSWORD_REGEX_ONE_LOWER_CASE_LETTER)
                && isStringContain(password, PASSWORD_REGEX_ONE_UPPER_CASE_LETTER);
    }

    public static boolean isEmailCorrect(String email) {
        return isNotEmptyOrNull(email) && isStringMatches(email, EMAIL_REGEX);
    }

    public static boolean isNameCorrect(String name) {
        return isNotEmptyOrNull(name) && isStringMatches(name, NAME_REGEX);
    }

    public static boolean isFirstNameCorrect(String name) {
        return isNotEmptyOrNull(name) && isStringMatches(name, USER_NAME_REGEX);
    }

    public static boolean isLastNameCorrect(String surname) {
        return isNotEmptyOrNull(surname) && isStringMatches(surname, USER_NAME_REGEX);
    }

    public static boolean isPhoneCorrect(String phone) {
        return isNotEmptyOrNull(phone) && isStringMatches(phone, PHONE_REGEX);
    }

    public static boolean isUniqueCodeCorrect(String uniqueCode) {
        return isNotEmptyOrNull(uniqueCode) && isStringMatches(uniqueCode, UNIQUE_KEY_REGEX);
    }

    public static boolean isIdCorrect(String id) {
        return isNotEmptyOrNull(id) && isStringMatches(id, ID_REGEX);
    }

    public static boolean isPriceCorrect(String price) {
        return isNotEmptyOrNull(price) && isStringMatches(price, PRICE_REGEX);
    }

    public static boolean isInfoCorrect(String info) {
        return isNotEmptyOrNull(info) && isStringMatches(info, INFO_REGEX);
    }

    public static boolean isAddressCorrect(String info) {
        return isNotEmptyOrNull(info) && isStringMatches(info, ADDRESS_REGEX);
    }

    private static boolean isNotEmptyOrNull(String string) {
        return string != null && !string.isEmpty();
    }

    private static boolean isStringMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    private static boolean isStringContain(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}


