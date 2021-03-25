package by.epam.project.util;

import by.epam.project.controller.async.AjaxData;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static by.epam.project.controller.parameter.ContentKey.*;
import static by.epam.project.controller.parameter.ErrorKey.ERROR;
import static by.epam.project.controller.parameter.ParameterKey.URL;

/**
 * The type File util.
 */
public class ImageUtil {
    private static final String CLOUD_NAME = "cloud_name";
    private static final String API_KEY = "api_key";
    private static final String API_SECRET = "api_secret";

    private static final String CLOUD_NAME_VALUE = "dkxnuhv44";
    private static final String API_KEY_VALUE = "276587525864389";
    private static final String API_SECRET_VALUE = "LtbxmO33K22U1Qi8NliGNE8ljp0";

    private static final String SLASH = "/";
    private static final String URL = "url";
    private static final String DOT = ".";
    private static final String OK = "ok";
    private static final String RESULT = "result";

    private ImageUtil() {}

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
                CLOUD_NAME, CLOUD_NAME_VALUE,
                API_KEY, API_KEY_VALUE,
                API_SECRET, API_SECRET_VALUE
        ));
        Map<String, Object> result = cloudinary.uploader().upload(fileByte, ObjectUtils.emptyMap());
        return (String) result.get(URL);
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
                CLOUD_NAME, CLOUD_NAME_VALUE,
                API_KEY, API_KEY_VALUE,
                API_SECRET, API_SECRET_VALUE
        ));

        Map<String, Object> result = cloudinary.uploader().destroy(fileName, ObjectUtils.emptyMap());
        return result.get(RESULT).equals(OK);
    }
}