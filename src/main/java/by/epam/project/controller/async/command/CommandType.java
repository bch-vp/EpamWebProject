package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.CheckLoginExistenceCommand;
import by.epam.project.controller.async.command.impl.UpdateProfileCommand;
import by.epam.project.controller.async.command.impl.UploadFileCommand;
import by.epam.project.controller.async.command.impl.guest.ChangePasswordByEmailCommand;
import by.epam.project.controller.async.command.impl.guest.SignUpCommand;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistenceCommand()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmailCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    UPLOAD_FILE(new UploadFileCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
