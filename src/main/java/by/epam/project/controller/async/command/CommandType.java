package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.SignUpCommand;

public enum CommandType {
    SIGN_UP(new SignUpCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;

    }
    public Command getCommand() {
        return command;
    }
}
