package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.AjaxData;
import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.CommandException;
import by.epam.project.exception.ServiceException;
import by.epam.project.model.entity.User;
import by.epam.project.model.service.UserService;
import by.epam.project.model.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.project.controller.parameter.ParameterKey.LANGUAGE;
import static by.epam.project.controller.parameter.ParameterKey.USER;

public class UploadProfileImageCommand implements Command {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public AjaxData execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AjaxData ajaxData = new AjaxData();

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        User user = (User) session.getAttribute(USER);
        String login = user.getLogin();

        if (!ServletFileUpload.isMultipartContent(request)) {
            ajaxData.setStatusHttp(HttpServletResponse.SC_BAD_REQUEST);
            return ajaxData;
        }

        List<FileItem> fileItems;
        try {
            fileItems = upload.parseRequest(request);
            ajaxData = userService.uploadUserImage(login, fileItems, language);
        } catch (ServiceException | FileUploadException exp) {
            throw new CommandException("Error during uploading user image", exp);
        }

        return ajaxData;
    }
}