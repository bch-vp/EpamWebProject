package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.Command;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class UploadFileCommand implements Command {
    private static final String UPLOAD_DIRECTORY = "/Users/ilyamurin/IdeaProjects/EpamWebProject/imagesss";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
    }
}
