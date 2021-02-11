package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.EmptyAsyncCommand;

public class CommandProvider {
    private CommandProvider(){}

    public static AsyncCommand provideCommand(String command) {
        AsyncCommand currentCommand;

        if (command == null || command.isEmpty()) {
            return new EmptyAsyncCommand();
        }
        try {
            currentCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exp) {
            currentCommand = new EmptyAsyncCommand();
        }
        return currentCommand;
    }
}
