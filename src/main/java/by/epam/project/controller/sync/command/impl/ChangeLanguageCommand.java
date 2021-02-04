package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.controller.parameter.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.project.controller.parameter.ParameterKey.*;

public class ChangeLanguageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();

        String givenLanguage = request.getParameter(LANGUAGE);
        if (givenLanguage.equals(ENGLISH_LANGUAGE) || givenLanguage.equals(RUSSIAN_LANGUAGE)) {
            session.setAttribute(LANGUAGE, givenLanguage);

            router.setRedirect();
            String redirectUrl = createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
            router.setCurrentPage(redirectUrl);
        } else {
            router = new Router(PagePath.ERROR_404);
        }
        return router;
    }

}
