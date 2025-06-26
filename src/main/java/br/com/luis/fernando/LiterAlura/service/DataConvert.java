package br.com.luis.fernando.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T getData(String json, Class<T> model) {
        try {
            return mapper.readValue(json, model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
