package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.*;

public enum CommandType {
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    CONFIRM_SIGN_UP(new ConfirmSignUpCommand()),
    EMPTY_COMMAND(new EmptyCommand()),
    PASSING_BY_ADMIN(new PassingByAdminCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    SIGN_OUT(new SignOutCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
