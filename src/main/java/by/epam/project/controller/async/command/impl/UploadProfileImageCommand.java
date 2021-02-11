package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static by.epam.project.controller.parameter.ParameterKey.USER;

public class UploadProfileImageCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    static final int FILE_MAX_SIZE = 1024 * 1024 * 2;
    private static final String FILE_TYPE = "image/jpg, image/png, image/gif, image/jpeg";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //error upload file TODO
            return;
        }

        if (fileItems.size() > 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //error only 1 file TODO
            return;
        }

        FileItem file = fileItems.get(0);
        if (file.getSize() > FILE_MAX_SIZE) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //error file bigger then max size TODO
            return;
        }

        String contentType = file.getContentType();
        if (!FILE_TYPE.contains(contentType)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //error wrong format file TODO
            return;
        }
        if(file.isFormField()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //error cant be formfield TODO
            return;
        }

        InputStream inputStream = file.getInputStream();
        try {
            userService.updateAvatarByLogin(login, inputStream);
        } catch (ServiceException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            //error during add to database TODO
            return;
        }

    }
}