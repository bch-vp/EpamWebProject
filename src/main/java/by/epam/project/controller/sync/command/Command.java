package by.epam.project.controller.sync.command;

import by.epam.project.controller.sync.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;

public interface Command {
    String REDIRECT_QUESTION_MARK = "?";
    String REDIRECT_EQUAL_SIGN = "=";

    Router execute(HttpServletRequest request);

    default String createRedirectURL(HttpServletRequest request, String commandName) {
        String redirectUrl = request.getContextPath() + request.getServletPath()
                + REDIRECT_QUESTION_MARK + COMMAND + REDIRECT_EQUAL_SIGN + commandName;
        return redirectUrl;
    }
}
