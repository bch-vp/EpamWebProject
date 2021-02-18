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
    LOAD_ALL_PRODUCTS_BY_CATEGORY_TO_CLIENT(new LoadAllProductsByCategoryToClientCommand()),
    ADD_PRODUCT_TO_SHOPPING_CART(new AddProductToShoppingCart()),
    DELETE_PROFILE_IMAGE(new DeleteProfileImage());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
