package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.*;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistenceCommand()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmailCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    LOAD_PROFILE_IMAGE(new LoadProfileImageCommand()),
    UPLOAD_PROFILE_IMAGE(new UploadProfileImageCommand()),
    CHANGE_PASSWORD_BY_OLD_PASSWORD(new ChangePasswordByOldPassCommand()),
    LOAD_ALL_CATEGORIES(new LoadAllCategoriesCommand()),
    LOAD_ALL_PRODUCTS_BY_CATEGORY(new LoadAllProductsByCategoryCommand()),
    ADD_PRODUCT_TO_SHOPPING_CART(new AddProductToShoppingCart()),
    UPDATE_PRODUCT_INFO(new UpdateProductInfoCommand()),
    UPDATE_PRODUCT_STATUS(new UpdateProductStatusCommand()),
    UPDATE_PRODUCT_CATEGORY(new UpdateProductCategoryCommand()),
    LOAD_SHOPPING_CART(new LoadShoppingCartCommand()),
    REMOVE_PRODUCT_FROM_SHOPPING_CART(new RemoveProductFromShoppingCartCommand()),
    LOAD_ALL_CLIENTS(new LoadAllClientsCommand()),
    UPDATE_CLIENT_STATUS(new UpdateClientStatusCommand()),
    CREATE_PRODUCT(new CreateProductCommand()),
    UPLOAD_PRODUCT_IMAGE(new UploadProductImageCommand()),
    UPDATE_CATEGORY_NAME(new UpdateCategoryNameCommand()),
    REMOVE_CATEGORY(new RemoveCategoryCommand()),
    CREATE_CATEGORY(new CreateCategoryCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    LOAD_ALL_ORDERS(new LoadAllOrdersCommand()),
    REMOVE_PROFILE_IMAGE(new RemoveProfileImageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
