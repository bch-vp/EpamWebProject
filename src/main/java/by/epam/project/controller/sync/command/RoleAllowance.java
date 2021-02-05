package by.epam.project.controller.sync.command;

import java.util.EnumSet;
import java.util.Set;

import static by.epam.project.controller.sync.command.CommandType.*;

public enum RoleAllowance {
    GUEST(EnumSet.of(
            SIGN_IN,
            PASSING_BY_GUEST,
//            PASSING_BY_CLIENT, //delete
            CHANGE_LANGUAGE
    )),
    CLIENT(EnumSet.of(
            PASSING_BY_CLIENT,
            CHANGE_LANGUAGE,
            SIGN_OUT
    )),
    ADMIN(EnumSet.of(
            PASSING_BY_ADMIN,
            CHANGE_LANGUAGE,
            SIGN_OUT
    ));

    private final Set<CommandType> commands;

    RoleAllowance(Set<CommandType> commandNames) {
        this.commands = commandNames;
    }

    public Set<CommandType> getCommands() {
        return commands;
    }
}
