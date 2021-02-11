package by.epam.project.controller.sync.command;

public class CommandProvider {
    private CommandProvider() {
    }

    public static Command provideCommand(String command) {
        Command currentCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
        return currentCommand;
    }
}