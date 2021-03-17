//package by.epam.project.controller.async.command.impl;
//
//import by.epam.project.controller.async.AjaxData;
//import by.epam.project.controller.async.command.Command;
//import by.epam.project.exception.ServiceException;
//import by.epam.project.model.entity.User;
//import by.epam.project.model.service.UserService;
//import by.epam.project.model.service.impl.UserServiceImpl;
//import by.epam.project.util.FileUtil;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Optional;
//
//import static by.epam.project.controller.parameter.ParameterKey.USER;
//
//
//public class RemoveProfileImageCommand implements Command {
//    private static final Logger logger = LogManager.getLogger();
//
//    private final UserService userService = UserServiceImpl.getInstance();
//
//    @Override
//    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//
//        User user = (User) session.getAttribute(USER);
//        String login = user.getLogin();
//
//        try {
//            Optional<String> URLOptional = userService.findAvatarURLByLogin(login);
//
//            if (URLOptional.isEmpty()) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }
//
//            String avatarURL = URLOptional.get();
//            FileUtil.remove(avatarURL);
//            userService.removeAvatarByLogin(login);
//        } catch (ServiceException | IOException exp) {
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//}
