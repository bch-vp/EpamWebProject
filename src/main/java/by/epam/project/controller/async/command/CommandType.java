package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.CheckLoginExistence;
import by.epam.project.controller.async.command.impl.guest.ChangePasswordByEmail;
import by.epam.project.controller.async.command.impl.guest.SignUpCommand;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    CHECK_LOGIN_EXISTENCE(new CheckLoginExistence()),
    CHANGE_PASSWORD_BY_EMAIL(new ChangePasswordByEmail());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
