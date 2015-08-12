package com.staliang.ymaps4j.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Alexandr_Badin on 12.08.2015
 */
public abstract class JSONUtil {

    public static <T> T fromJSON(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
