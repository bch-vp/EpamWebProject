package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.*;

public enum CommandType {
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    PASSING_BY_ADMIN(new PassingByAdminCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    CONFIRM_SIGN_UP(new ConfirmSignUpCommand()),
    SIGN_OUT(new SignOutCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return command;
    }
}
