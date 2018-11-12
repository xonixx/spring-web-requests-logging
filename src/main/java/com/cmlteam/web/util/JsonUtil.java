package com.cmlteam.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class JsonUtil {
    private JsonUtil() {
    }

    private static final ObjectMapper OBJECT_MAPPER = prepareObjectMapper();

    private static ObjectMapper prepareObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(df);
        return objectMapper;
    }

    public static String prettyPrintJson(String json) {
        if (json == null)
            return null;
        try {
            Object parsed = OBJECT_MAPPER.readValue(json, Object.class);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(parsed);
        } catch (IOException ignore) {
            return json;
        }
    }
}

