package by.epam.project.util;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.project.controller.parameter.ParameterKey.ENGLISH_LANGUAGE;


public class ContentUtil {
    private ContentUtil() {
    }

    private static final String RESOURCES_LANGUAGE = "content.language";
    private static final Locale DEFAULT_LANGUAGE = new Locale(ENGLISH_LANGUAGE);

    public static String getWithLocale(String language, String key) {
        Locale currentLocale = DEFAULT_LANGUAGE;
        if (language != null) {
            currentLocale = new Locale(language);
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES_LANGUAGE, currentLocale);
        return resourceBundle.getString(key);
    }
}

