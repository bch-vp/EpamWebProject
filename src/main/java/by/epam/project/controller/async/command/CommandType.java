package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.*;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistenceCommand()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmailCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    LOAD_PROFILE_IMAGE(new LoadProfileImageCommand()),
    UPLOAD_PROFILE_IMAGE(new UploadProfileImageCommand()),
    DELETE_PROFILE_IMAGE(new DeleteProfileImage());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
