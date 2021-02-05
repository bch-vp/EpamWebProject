package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.ChangeLanguageCommand;
import by.epam.project.controller.sync.command.impl.admin.PassingByAdminCommand;
import by.epam.project.controller.sync.command.impl.client.SignOut;
import by.epam.project.controller.sync.command.impl.guest.SignInCommand;
import by.epam.project.controller.sync.command.impl.guest.PassingByGuestCommand;
import by.epam.project.controller.sync.command.impl.client.PassingByClientCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    PASSING_BY_ADMIN(new PassingByAdminCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_OUT(new SignOut());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
