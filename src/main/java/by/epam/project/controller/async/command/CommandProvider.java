package by.epam.project.controller.async.command;

import by.epam.project.controller.async.command.impl.EmptyCommand;

public class CommandProvider {
    private CommandProvider(){}

    public static Command provideCommand(String command) {
        Command currentCommand;

        if (command == null || command.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            currentCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exp) {
            currentCommand = new EmptyCommand();
        }
        return currentCommand;
    }
}
