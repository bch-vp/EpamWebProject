//package by.epam.project.controller.async.command.impl;
//
//import by.epam.project.controller.async.AjaxData;
//import by.epam.project.controller.async.command.Command;
//import by.epam.project.exception.ServiceException;
//import by.epam.project.model.entity.Order;
//import by.epam.project.model.service.UserService;
//import by.epam.project.model.service.impl.UserServiceImpl;
//import by.epam.project.util.JsonUtil;
//import by.epam.project.validator.ServiceValidator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static by.epam.project.controller.parameter.ParameterKey.ID_ORDER;
//import static by.epam.project.controller.parameter.ParameterKey.ID_STATUS;
//
//public class UpdateOrderStatusCommand implements Command {
//    private static final Logger logger = LogManager.getLogger();
//
//    private final UserService userService = UserServiceImpl.getInstance();
//
//    @Override
//    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);
//
//            String idOrderString = (String) requestParameters.get(ID_ORDER);
//            String idStatusString = (String) requestParameters.get(ID_STATUS);
//            if (!ServiceValidator.isIdCorrect(idOrderString)
//                    || !ServiceValidator.isIdCorrect(idStatusString)) {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                return;
//            }
//
//            long idOrder = Long.parseLong(idOrderString);
//            long idStatus = Long.parseLong(idStatusString);
//
//            Optional<Order.Status> statusOptional = userService.findOrderStatusById(idStatus);
//            if (statusOptional.isEmpty()) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }
//
//            boolean isUpdated = userService.updateOrderStatusById(idOrder, idStatus);
//            if (!isUpdated) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            }
//        } catch (ServiceException | IOException exp) {
//            logger.error(exp);
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//}
