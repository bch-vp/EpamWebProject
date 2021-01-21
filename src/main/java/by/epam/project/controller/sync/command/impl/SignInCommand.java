package by.epam.project.controller.sync.command.impl;

import by.epam.project.controller.constant.ErrorKey;
import by.epam.project.controller.constant.SessionAttributeKey;
import by.epam.project.controller.constant.PagePath;
import by.epam.project.controller.constant.PropertieKey;
import by.epam.project.controller.sync.Router;
import by.epam.project.controller.sync.command.*;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.Optional;

import static by.epam.project.controller.constant.RequestParameterKey.LANGUAGE;

public class SignInCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();



//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try {
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null)
//                jb.append(line);
//        } catch (Exception e) { /*report an error*/ }




        String language = (String) session.getAttribute(LANGUAGE);

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            Optional<User> currentUser = userService.signInUser(login, password);
            if (currentUser.isPresent()) {
                router.setCurrentPage(PagePath.CLIENT);
            } else {

                String error = ContentUtil.getWithLocale(language, PropertieKey.ERROR_SIGN_IN_NOT_FOUND);
                session.setAttribute(ErrorKey.LOGIN_NOT_FOUND, error);

                router.setRedirect();
                String redirectUrl = createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
                router.setCurrentPage(redirectUrl);
            }
        } catch (ServiceException exp) {
            String propertieKey = exp.getCause().getMessage();// ??????????
            String error = ContentUtil.getWithLocale(language, propertieKey);
            session.setAttribute(ErrorKey.DATABASE_CONNECTION_NOT_RECEIVED, error);

            router.setRedirect();
            String redirectUrl = createRedirectURL(request, CommandType.PASSING_BY_GUEST.toString().toLowerCase());
            router.setCurrentPage(redirectUrl);
        }
        return router;
    }
}
