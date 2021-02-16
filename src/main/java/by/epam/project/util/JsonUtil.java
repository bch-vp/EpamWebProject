package by.epam.project.util;

import by.epam.project.util.ContentUtil;
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

    private static final String COMMAND_NAME = "command";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    private JsonUtil() {
    }

    public static void writeJsonToResponse(HttpServletResponse response, String errorKey, String contentKey, String language)
            throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        String contentValue = ContentUtil.getWithLocale(language, contentKey);
        responseMap.put(errorKey, contentValue);

        String json = toJson(responseMap);

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        response.getWriter().write(json);
    }

    public static void writeJsonToResponse(HttpServletResponse response, String json) throws IOException {
        if(json != null && !json.isEmpty()) {
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().write(json);
        }
    }

    public static void writeJsonToResponse(HttpServletResponse response, String key, String value) throws IOException {
        String json = toJson(key, value);
        writeJsonToResponse(response, json);
    }

    public static String toJson(String key, String value) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put(key, value);
        String json = toJson(responseMap);
        return json;
    }

    public static<T> String toJson(String key, List<T> list) throws IOException {
        Map<String, List<T>> responseMap = new HashMap<>();
        responseMap.put(key, list);
        String json = toJson(responseMap);
        return json;
    }

    public static String toJson(Map map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

    public static <T> T toMap(InputStream inputStream, Class<T> clazz) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

    public static JsonNode addNodeToJsonTree(JsonNode rootNode, String key, String value, String... paths) {
        if(rootNode == null){
            rootNode = objectMapper.createObjectNode();
        }

        JsonNode node = rootNode;
        for (String path : paths){
            node = node.path(path);
        }
        JsonNode n = ((ObjectNode)node).put(key, value);
        return  rootNode;
    }

    public static JsonNode addObjectToJsonTree(JsonNode rootNode, String object, String... paths) {
        if(rootNode == null){
            rootNode = objectMapper.createObjectNode();
        }

        JsonNode node = rootNode;
        for (String path : paths){
            node = node.path(path);
        }
        JsonNode n = ((ObjectNode)node).putObject(object);
        return  rootNode;
    }

    public static String jsonTreeToJson(JsonNode jsonNode){
        return jsonNode.toString();
    }
}
