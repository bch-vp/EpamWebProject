package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.ChangeLanguageCommand;
import by.epam.project.controller.sync.command.impl.PassingByAdminCommand;
import by.epam.project.controller.sync.command.impl.SignOutCommand;
import by.epam.project.controller.sync.command.impl.SignInCommand;
import by.epam.project.controller.sync.command.impl.PassingByGuestCommand;
import by.epam.project.controller.sync.command.impl.PassingByClientCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    PASSING_BY_ADMIN(new PassingByAdminCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_OUT(new SignOutCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
