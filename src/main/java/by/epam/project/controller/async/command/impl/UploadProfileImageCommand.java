package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.ContentUtil;
import by.epam.project.controller.async.command.impl.util.JsonUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.LANGUAGE;
import static by.epam.project.controller.parameter.ParameterKey.USER;
import static by.epam.project.controller.parameter.ContentKey.*;

public class UploadProfileImageCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    static final int FILE_MAX_SIZE = 1024 * 512;
    private static final String FILE_TYPE = "image/jpg, image/png, image/jpeg";

    private static final int FILES_COUNT = 1;
    private static final int FIRST = 0;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        User user = (User) session.getAttribute(USER);
        String login = user.getLogin();

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        List<FileItem> fileItems;
        try {
            try {
                fileItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_PARSE_REQUEST, language);
                return;
            }

            if (fileItems.size() != FILES_COUNT) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_COUNT_ALLOWED_FILES, language);
                return;
            }

            FileItem file = fileItems.get(FIRST);
            if (file.getSize() > FILE_MAX_SIZE) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_MAX_SIZE, language);
                return;
            }

            if (file.isFormField()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_FORM_FIELD, language);
                return;
            }

            String contentType = file.getContentType();
            if (!FILE_TYPE.contains(contentType)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonUtil.writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_FORMAT, language);
                return;
            }

            InputStream inputStream = file.getInputStream();
            userService.updateAvatarByLogin(login, inputStream);
        } catch (ServiceException | IOException e) {
            logger.error(e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}