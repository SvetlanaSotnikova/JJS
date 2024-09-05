package org.Sem3.HM;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.List;

public class Serialization {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void savePersonToFile(String fileName, Object obj) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), obj);
                System.out.println("object serialized to file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<?> loadPersonFromFile(String fileName, Class<?> type) {
        try {
            if (fileName.endsWith(".json")) {
                return objectMapper.readValue(new File(fileName),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, type));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
