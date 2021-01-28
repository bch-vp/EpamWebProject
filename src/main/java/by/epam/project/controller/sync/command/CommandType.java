package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.ChangeLanguageCommand;
import by.epam.project.controller.sync.command.impl.guest.SignInCommand;
import by.epam.project.controller.sync.command.impl.guest.PassingByGuestCommand;
import by.epam.project.controller.sync.command.impl.client.PassingByClientCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
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
