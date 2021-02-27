package by.epam.project.model.typerole;

import java.util.Set;


public enum RolePermission {
    GUEST(Set.of(
            "PASSING_BY_GUEST",
            "CHANGE_LANGUAGE",
            "CHECK_LOGIN_EXISTENCE",
            "SIGN_IN",
            "SIGN_UP",
            "CONFIRM_SIGN_UP",
            "CHANGE_PASSWORD_BY_EMAIL"
    )),
    CLIENT(Set.of(
            "PASSING_BY_CLIENT",
            "CHANGE_LANGUAGE",
            "UPDATE_PROFILE",
            "LOAD_PROFILE_IMAGE",
            "UPLOAD_PROFILE_IMAGE",
            "REMOVE_PROFILE_IMAGE",
            "LOAD_ALL_CATEGORIES",
            "LOAD_ALL_PRODUCTS_BY_CATEGORY",
            "CHANGE_PASSWORD_BY_OLD_PASSWORD",
            "ADD_PRODUCT_TO_SHOPPING_CART",
            "LOAD_SHOPPING_CART",
            "CREATE_ORDER",
            "LOAD_ALL_ORDERS",
            "REMOVE_PRODUCT_FROM_SHOPPING_CART",
            "SIGN_OUT"
    )),
    ADMIN(Set.of(
            "PASSING_BY_ADMIN",
            "CHANGE_LANGUAGE",
            "LOAD_PROFILE_IMAGE",
            "UPLOAD_PROFILE_IMAGE",
            "UPDATE_PROFILE",
            "REMOVE_PROFILE_IMAGE",
            "LOAD_ALL_CATEGORIES",
            "LOAD_ALL_PRODUCTS_BY_CATEGORY",
            "UPDATE_PRODUCT_INFO",
            "UPDATE_PRODUCT_STATUS",
            "UPDATE_PRODUCT_CATEGORY",
            "CHANGE_PASSWORD_BY_OLD_PASSWORD",
            "LOAD_ALL_CLIENTS",
            "UPDATE_CLIENT_STATUS",
            "CREATE_PRODUCT",
            "CREATE_CATEGORY",
            "UPDATE_CATEGORY_NAME",
            "REMOVE_CATEGORY",
            "UPLOAD_PRODUCT_IMAGE",
            "SIGN_OUT"
    ));
    private final Set<String> commands;

    RolePermission(Set<String> commandNames) {
        this.commands = commandNames;
    }

    public Set<String> getCommands() {
        return commands;
    }
}
