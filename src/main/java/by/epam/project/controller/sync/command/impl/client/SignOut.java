package by.epam.project.controller.sync.command.impl.client;

import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.sync.command.CommandType;
import by.epam.project.util.URLUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOut implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.invalidate();

        router.setRedirect();
        String redirectURL = URLUtil.createRedirectURL(request,
                CommandType.PASSING_BY_GUEST.toString().toLowerCase());
        router.setCurrentPage(redirectURL);

        return router;
    }
}
