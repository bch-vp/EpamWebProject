package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.CheckLoginExistenceAsyncCommand;
import by.epam.project.controller.async.command.impl.UpdateProfileAsyncCommand;
import by.epam.project.controller.async.command.impl.LoadProfileImageAsyncCommand;
import by.epam.project.controller.async.command.impl.UploadProfileImageAsyncCommand;
import by.epam.project.controller.async.command.impl.ChangePasswordByEmailAsyncCommand;
import by.epam.project.controller.async.command.impl.SignUpAsyncCommand;

public enum CommandType {
    SIGN_UP(new SignUpAsyncCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistenceAsyncCommand()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmailAsyncCommand()),
    UPDATE_PROFILE(new UpdateProfileAsyncCommand()),
    LOAD_PROFILE_IMAGE(new LoadProfileImageAsyncCommand()),
    UPLOAD_PROFILE_IMAGE(new UploadProfileImageAsyncCommand());

    private final AsyncCommand command;

    CommandType(AsyncCommand command) {
        this.command = command;

    }
    public AsyncCommand getCommand() {
        return command;
    }
}
