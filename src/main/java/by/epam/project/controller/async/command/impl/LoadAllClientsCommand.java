//package by.epam.project.controller.async.command.impl;
//
//import by.epam.project.controller.async.AjaxData;
//import by.epam.project.controller.async.command.Command;
//import by.epam.project.exception.ServiceException;
//import by.epam.project.model.entity.User;
//import by.epam.project.model.service.UserService;
//import by.epam.project.model.service.impl.UserServiceImpl;
//import by.epam.project.util.JsonUtil;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static by.epam.project.controller.parameter.ParameterKey.USERS;
//
//public class LoadAllClientsCommand implements Command {
//    private static final Logger logger = LogManager.getLogger();
//
//    private final UserService userService = UserServiceImpl.getInstance();
//
//    @Override
//    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            List<User> users = userService.findAllClients();
//            String json = JsonUtil.toJson(USERS, users);
//            JsonUtil.writeJsonToResponse(response, json);
//        } catch (ServiceException | IOException exp) {
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//}
