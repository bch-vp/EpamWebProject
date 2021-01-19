package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.ChangeLanguageCommand;
import by.epam.project.controller.sync.command.impl.SignInCommand;
import by.epam.project.controller.sync.command.impl.SignUpCommand;
import by.epam.project.controller.sync.command.impl.page.PassingByGuestCommand;
import by.epam.project.controller.sync.command.impl.page.PassingByClientCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
