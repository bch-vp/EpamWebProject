package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.*;

public enum CommandType {
    ADD_PRODUCT_TO_SHOPPING_CART(new AddProductToShoppingCart()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmailCommand()),
    CHANGE_PASSWORD_BY_OLD_PASSWORD(new ChangePasswordByOldPassCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistenceCommand()),
    CREATE_CATEGORY(new CreateCategoryCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    CREATE_PRODUCT(new CreateProductCommand()),
    LOAD_ALL_CATEGORIES(new LoadAllCategoriesCommand()),
    LOAD_ALL_CLIENTS(new LoadAllClientsCommand()),
    LOAD_ALL_ORDERS(new LoadAllOrdersCommand()),
    LOAD_ALL_PRODUCTS_BY_CATEGORY(new LoadAllProductsByCategoryCommand()),
    LOAD_PROFILE_IMAGE(new LoadProfileImageCommand()),
    LOAD_SHOPPING_CART(new LoadShoppingCartCommand()),
    REMOVE_CATEGORY(new RemoveCategoryCommand()),
    REMOVE_PRODUCT_FROM_SHOPPING_CART(new RemoveProductFromShoppingCartCommand()),
    REMOVE_PROFILE_IMAGE(new RemoveProfileImageCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    UPDATE_CATEGORY_NAME(new UpdateCategoryNameCommand()),
    UPDATE_CLIENT_STATUS(new UpdateClientStatusCommand()),
    UPDATE_ORDER_STATUS(new UpdateOrderStatusCommand()),
    UPDATE_PRODUCT_CATEGORY(new UpdateProductCategoryCommand()),
    UPDATE_PRODUCT_INFO(new UpdateProductInfoCommand()),
    UPDATE_PRODUCT_STATUS(new UpdateProductStatusCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    UPLOAD_PRODUCT_IMAGE(new UploadProductImageCommand()),
    UPLOAD_PROFILE_IMAGE(new UploadProfileImageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return command;
    }
}
