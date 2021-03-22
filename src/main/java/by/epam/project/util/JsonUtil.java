package by.epam.project.util;

import by.epam.project.controller.async.AjaxData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";
    private static final int EMPTY_PRIMITIVE = 0;

    private JsonUtil() {
    }

    public static void writeJsonToAjaxData(AjaxData ajaxData, String errorKey, String contentKey,
                                           String language) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        String contentValue = ContentUtil.getWithLocale(language, contentKey);
        responseMap.put(errorKey, contentValue);

        String json = toJson(responseMap);
        ajaxData.setJson(json);
    }

    public static void writeJsonToResponse(HttpServletResponse response, String json) throws IOException {
        if (json != null && !json.isEmpty()) {
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().write(json);
        }
    }

    public static void writeAjaxDataToResponse(HttpServletResponse response, AjaxData ajaxData) throws IOException {
        if (ajaxData.getStatusHttp() != HttpServletResponse.SC_OK) {
            response.setStatus(ajaxData.getStatusHttp());
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        writeJsonToResponse(response, ajaxData.getJson());
    }

    public static void writeJsonToAjaxData(AjaxData ajaxData, String key, String value) throws IOException {
        String json = toJson(key, value);
        ajaxData.setJson(json);
    }

    public static String toJson(String key, String value) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put(key, value);
        String json = toJson(responseMap);
        return json;
    }

    public static <T> String toJson(String key, List<T> list) throws IOException {
        Map<String, List<T>> responseMap = new HashMap<>();
        responseMap.put(key, list);
        String json = toJson(responseMap);
        return json;
    }

    public static String toJson(Map map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

    public static HashMap toMap(InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, HashMap.class);
    }

    public static <T> String toJson(T t) throws IOException {
        return objectMapper.writeValueAsString(t);
    }

    public static JsonNode addNodeToJsonTree(JsonNode rootNode, String key, String value, String... paths) {
        JsonNode node = rootNode;
        for (String path : paths) {
            node = node.path(path);
        }
        ((ObjectNode) node).put(key, value);
        return rootNode;
    }

    public static JsonNode createJsonTree(String object) {
        JsonNode rootNode = objectMapper.createObjectNode();
        ((ObjectNode) rootNode).putObject(object);
        return rootNode;
    }

    public static void writeJsonTreeToResponse(AjaxData ajaxData, JsonNode jsonNode) {
        String json = jsonNode.toString();
        ajaxData.setJson(json);
    }
}
