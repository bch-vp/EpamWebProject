package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.controller.parameter.ErrorKey;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import by.epam.project.util.JsonUtil;
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
import static by.epam.project.controller.parameter.ParameterKey.USER;
import static by.epam.project.controller.parameter.PropertieKey.*;

public class UploadProfileImageCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    static final int FILE_MAX_SIZE = 1024 * 1024 * 2;
    private static final String FILE_TYPE = "image/jpg, image/png, image/jpeg";

    private static final int FILES_COUNT = 1;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

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
                writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_PARSE_REQUEST);
                return;
            }

            //allowed only 1 file
            if (fileItems.size() != FILES_COUNT) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_COUNT_ALLOWED_FILES);
                return;
            }

            //file bigger then max size
            FileItem file = fileItems.get(0);
            if (file.getSize() > FILE_MAX_SIZE) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_MAX_SIZE);
                return;
            }


            //can't be form field
            if (file.isFormField()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_FORM_FIELD);
                return;
            }


            //wrong format file
            String contentType = file.getContentType();
            if (!FILE_TYPE.contains(contentType)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeJsonToResponse(response, ERROR, ERROR_PROFILE_AVATAR_FORMAT);
                return;
            }

            InputStream inputStream = file.getInputStream();
            userService.updateAvatarByLogin(login, inputStream);
        } catch (ServiceException | IOException e) {
            logger.error(e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void writeJsonToResponse(HttpServletResponse response, String key, String value) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put(key, value);

        String json = JsonUtil.toJson(responseMap);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        response.getWriter().write(json);
    }
}