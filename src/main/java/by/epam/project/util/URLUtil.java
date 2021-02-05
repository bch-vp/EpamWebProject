package by.epam.project.util;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;

public class URLUtil {
    private static final String REDIRECT_QUESTION_MARK = "?";
    private static final String REDIRECT_EQUAL_SIGN = "=";

    private URLUtil() {
    }

    public static String createRedirectURL(HttpServletRequest request, String commandName) {
        String redirectUrl = request.getContextPath() + request.getServletPath()
                + REDIRECT_QUESTION_MARK + COMMAND + REDIRECT_EQUAL_SIGN + commandName;
        return redirectUrl;
    }
}
