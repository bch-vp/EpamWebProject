package by.epam.project.controller.parameter;

public class PropertieKey {
    public static final String SUCCESS = "success";
    public static final String INFO_CHANGING_PASSWORD_EMAIL_CONFIRMATION = "changing_password.guest.info.notification";
    public static final String ERROR_CHANGING_PASSWORD_EMAIL_INCORRECT =
            "changing_password.guest.error.email_incorrect";
    public static final String ERROR_CHANGING_PASSWORD_UNIQUE_KEY_INCORRECT =
            "changing_password.guest.error.unique_key_incorrect";
    public static final String ERROR_CHANGING_PASSWORD_GUEST_TIME_EXPIRED =
            "changing_password.guest.error.time_expired";

    public static final String EMAIL_SUBJECT_ACTIVATION_SIGN_UP = "email.subject.activation_sign_up";
    public static final String EMAIL_SUBJECT_GUEST_CHANGING_PASSWORD = "email.subject.guest.changing_password";
    public static final String EMAIL_BODY_ACTIVATION_SIGN_UP = "email.body.activation_sign_up";
    public static final String EMAIL_BODY_GUEST_CHANGING_PASSWORD = "email.body.guest.changing_password";

    public static final String ERROR_SIGN_IN_NOT_FOUND = "sign_in_component.error.not_found";
    public static final String ERROR_SIGN_UP_LOGIN_NOT_UNIQUE = "sign_up_component.error.login_not_unique";
    public static final String ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE =
            "sign_up_component.error.telephone_number_not_unique";
    public static final String ERROR_SIGN_UP_EMAIL_NOT_UNIQUE = "sign_up_component.error.email_not_unique";
    public static final String ERROR_DATABASE_CONNECTION_NOT_RECEIVED = "database.error.connection_not_received";


    private PropertieKey() {
    }
}
