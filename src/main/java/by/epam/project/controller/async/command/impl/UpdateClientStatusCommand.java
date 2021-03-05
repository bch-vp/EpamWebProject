package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
import by.epam.project.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ParameterKey.ID_STATUS;
import static by.epam.project.controller.parameter.ParameterKey.ID_USER;

public class UpdateClientStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map requestParameters = JsonUtil.toMap(request.getInputStream(), HashMap.class);

            String idUserString = (String) requestParameters.get(ID_USER);
            String idStatusString = (String) requestParameters.get(ID_STATUS);
            if (!ServiceValidator.isIdCorrect(idUserString)
                    || !ServiceValidator.isIdCorrect(idStatusString)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            long idUser = Long.parseLong(idUserString);
            long idStatus = Long.parseLong(idStatusString);

            Optional<User.Status> categoryOptional = userService.findStatusById(idStatus);
            if (categoryOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            boolean isUpdated = userService.updateUserStatus(idUser, idStatus);
            if (!isUpdated) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException | IOException exp) {
            logger.error(exp);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

