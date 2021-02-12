package by.epam.project.model.typerole;

import java.util.Set;


public enum RolePermission {
    GUEST(Set.of(
            "PASSING_BY_GUEST",
            "CHANGE_LANGUAGE",
            "SIGN_IN",
            "SIGN_UP",
            "CHANGE_PASSWORD_BY_EMAIL"
    )),
    CLIENT(Set.of(
            "PASSING_BY_CLIENT",
            "CHANGE_LANGUAGE",
            "UPDATE_PROFILE",
            "LOAD_PROFILE_IMAGE",
            "UPLOAD_PROFILE_IMAGE",
            "DELETE_PROFILE_IMAGE",
            "CHANGE_PASSWORD_BY_OLD_PASSWORD",// TODO
            "CHECK_LOGIN_EXISTENCE",
            "SIGN_OUT"
    )),
    ADMIN(Set.of(
            "PASSING_BY_ADMIN",
            "CHANGE_LANGUAGE",
            "SIGN_OUT"
    ));

    private final Set<String> commands;

    RolePermission(Set<String> commandNames) {
        this.commands = commandNames;
    }

    public Set<String> getCommands() {
        return commands;
    }
}
