package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.util.URLUtil;

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

            // check current command
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            if(currentPage == null){
                String redirectURL = URLUtil.createRedirectURL(request,
                        CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                router.setCurrentPage(redirectURL);
                return router;
            }
            switch (currentPage){
                case PASSING_BY_CLIENT: {
                    String redirectURL = URLUtil.createRedirectURL(request,
                            CommandType.PASSING_BY_CLIENT.toString().toLowerCase());
                    router.setCurrentPage(redirectURL);
                }
                case PASSING_BY_ADMIN: {
                    String redirectURL = URLUtil.createRedirectURL(request,
                            CommandType.PASSING_BY_ADMIN.toString().toLowerCase());
                    router.setCurrentPage(redirectURL);
                }
                default: {
                    String redirectURL = URLUtil.createRedirectURL(request,
                            CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                    router.setCurrentPage(redirectURL);

                }
            }
            return router;
        } else {
            router = new Router(PagePath.ERROR_404);
        }
        return router;
    }

}
