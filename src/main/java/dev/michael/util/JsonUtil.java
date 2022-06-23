package dev.michael.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtil {

    /**
     * object to Json String
     *
     * @return String
     */
    public static String getObjectJsonString(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
        }
        return json;
    }
}
