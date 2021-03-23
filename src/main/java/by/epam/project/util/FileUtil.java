package by.epam.project.util;

import by.epam.project.controller.async.AjaxData;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.project.controller.parameter.ContentKey.*;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.URL;

/**
 * The type File util.
 */
public class FileUtil {
    private static final String SLASH = "/";
    private static final String DOT = ".";
    private static final String OK = "ok";
    private static final String RESULT = "result";

    private FileUtil() {
    }

    /**
     * Save string.
     *
     * @param file the file
     * @return the string
     * @throws IOException the io exception
     */
    public static String save(FileItem file) throws IOException {
        byte[] fileByte = file.get();
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dkxnuhv44",
                "api_key", "276587525864389",
                "api_secret", "LtbxmO33K22U1Qi8NliGNE8ljp0"
        ));
        Map<String, Object> result = cloudinary.uploader().upload(fileByte, ObjectUtils.emptyMap());
        return (String) result.get("url");
    }

    /**
     * Remove boolean.
     *
     * @param url the url
     * @return the boolean
     * @throws IOException the io exception
     */
    public static boolean remove(String url) throws IOException {
        int lastIndexOfSlash = url.lastIndexOf(SLASH);
        int lastIndexOfDOT = url.lastIndexOf(DOT);
        String fileName = url.substring(lastIndexOfSlash + 1, lastIndexOfDOT);

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dkxnuhv44",
                "api_key", "276587525864389",
                "api_secret", "LtbxmO33K22U1Qi8NliGNE8ljp0"
        ));

        Map<String, Object> result = cloudinary.uploader().destroy(fileName, ObjectUtils.emptyMap());
        return result.get(RESULT).equals(OK);
    }
}