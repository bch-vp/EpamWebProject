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


    //    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
//        try {
//            return objectMapper.readValue(json, typeReference);
//        } catch (JsonParseException e) {
//            logger.error(e.getMessage(), e);
//        } catch (JsonMappingException e) {
//            logger.error(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//
//    /**
//     * json string convert to map with javaBean
//     */
//    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
//            throws Exception {
//        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
//                new TypeReference<Map<String, T>>() {
//                });
//        Map<String, T> result = new HashMap<String, T>();
//        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
//            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
//        }
//        return result;
//    }

//    /**
//     * json array string convert to list with javaBean
//     */
//    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
//            throws Exception {
//        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
//                new TypeReference<List<T>>() {
//                });
//        List<T> result = new ArrayList<T>();
//        for (Map<String, Object> map : list) {
//            result.add(map2pojo(map, clazz));
//        }
//        return result;
//    }

//    /**
//     * map convert to javaBean
//     */
//    public static <T> T map2pojo(Map map, Class<T> clazz) {
//        return objectMapper.convertValue(map, clazz);
//    }

}
