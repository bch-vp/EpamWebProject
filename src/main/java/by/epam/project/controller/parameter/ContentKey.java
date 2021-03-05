package by.epam.project.controller.parameter;

public class ContentKey {
    public static final String INFO_CHANGING_PASSWORD_EMAIL_CONFIRMATION = "form_component.info.check_email.unique_code";
    public static final String ERROR_CHANGING_PASSWORD_EMAIL_INCORRECT =
            "form_component.error.email_incorrect";
    public static final String ERROR_CHANGING_PASSWORD_UNIQUE_KEY_INCORRECT =
            "form_component.error.unique_key_incorrect";
    public static final String ERROR_CHANGING_PASSWORD_GUEST_TIME_EXPIRED =
            "form_component.error.time_expired";
    public static final String ERROR_PROFILE_OLD_PASSWORD_NOT_EQUAL_LOGIN_PASSWORD =
            "form_component.error.old_password_not_equal_login_password";

    public static final String ERROR_NAME_NOT_UNIQUE = "form_component.error.name_not_unique";


    public static final String EMAIL_SUBJECT_ACTIVATION_SIGN_UP = "email.subject.activation_sign_up";
    public static final String EMAIL_SUBJECT_GUEST_CHANGING_PASSWORD = "email.subject.guest.changing_password";
    public static final String EMAIL_BODY_ACTIVATION_SIGN_UP = "email.body.activation_sign_up";
    public static final String EMAIL_BODY_GUEST_CHANGING_PASSWORD = "email.body.guest.changing_password";

    public static final String ERROR_SIGN_IN_NOT_ACTIVATED = "form_component.error.not_activated";
    public static final String ERROR_SIGN_IN_BANNED = "form_component.error.banned";
    public static final String ERROR_SIGN_UP_LOGIN_NOT_UNIQUE = "form_component.error.login_not_unique";
    public static final String ERROR_SIGN_UP_TELEPHONE_NUMBER_NOT_UNIQUE =
            "form_component.error.telephone_number_not_unique";
    public static final String ERROR_SIGN_UP_EMAIL_NOT_UNIQUE = "form_component.error.email_not_unique";

    public static final String ERROR_PROFILE_AVATAR_COUNT_ALLOWED_FILES = "profile_component.avatar.error.allowed_files_count";
    public static final String ERROR_PROFILE_AVATAR_MAX_SIZE = "profile_component.avatar.error.max_size";
    public static final String ERROR_PROFILE_AVATAR_FORMAT = "profile_component.avatar.error.format";
    public static final String ERROR_PROFILE_AVATAR_FORM_FIELD = "profile_component.avatar.error.form_field";
    public static final String ERROR_PROFILE_AVATAR_PARSE_REQUEST = "profile_component.avatar.error.parse_request";

    private ContentKey() {
    }
}
