package by.epam.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonUtil {
    private static final JsonUtil instance = new JsonUtil();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static JsonUtil getInstance() {
        return instance;
    }

    public static <T> T toObject(String json, Class<T> clazz) throws JsonProcessingException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw e;
//            logger.error(e.getMessage(), e);
        }
    }

    public static String toJson(Map map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }


    public static <T> String toJson(T entity) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (IOException e) {
            throw e;
//            logger.error(e.getMessage(), e);
        }
    }

    public static <T> T toMap(InputStream inputStream, Class<T> clazz)
            throws IOException {
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
