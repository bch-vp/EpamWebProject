package by.epam.project.controller.command;

import by.epam.project.controller.command.impl.ChangeLanguageCommand;
import by.epam.project.controller.command.impl.SignInCommand;
import by.epam.project.controller.command.impl.SignUpCommand;
import by.epam.project.controller.command.impl.page.PassingByGuestCommand;
import by.epam.project.controller.command.impl.page.PassingByClientCommand;
import by.epam.project.controller.command.impl.SendMail;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SEND_MAIL(new SendMail());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
