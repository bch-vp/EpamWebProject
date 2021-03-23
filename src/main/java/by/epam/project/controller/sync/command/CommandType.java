package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.command.impl.*;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Change language.
     */
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    /**
     * The Confirm sign up.
     */
    CONFIRM_SIGN_UP(new ConfirmSignUpCommand()),
    /**
     * The Empty command.
     */
    EMPTY_COMMAND(new EmptyCommand()),
    /**
     * The Passing by admin.
     */
    PASSING_BY_ADMIN(new PassingByAdminCommand()),
    /**
     * The Passing by client.
     */
    PASSING_BY_CLIENT(new PassingByClientCommand()),
    /**
     * The Passing by guest.
     */
    PASSING_BY_GUEST(new PassingByGuestCommand()),
    /**
     * The Sign out.
     */
    SIGN_OUT(new SignOutCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
}
