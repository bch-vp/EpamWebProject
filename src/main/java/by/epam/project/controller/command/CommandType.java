package by.epam.project.controller.command;

import by.epam.project.controller.command.impl.SignInCommand;
import by.epam.project.controller.command.impl.SignUpCommand;
import by.epam.project.controller.command.impl.page.PassingHomeCommand;
import by.epam.project.controller.command.impl.page.PassingHomeUserCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    SIGN_UP(new SignUpCommand()),
    PASSING_HOME(new PassingHomeCommand()),
    PASSING_HOME_USER(new PassingHomeUserCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
