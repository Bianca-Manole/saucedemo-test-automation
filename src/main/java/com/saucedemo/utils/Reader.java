package com.saucedemo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Reader {
    private static final String TEST_DATA_DIR = "/testdata/";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Reader() {
    }

    public static JsonNode json(String name) {
        String resource = TEST_DATA_DIR + name + ".json";
        try (InputStream in = Reader.class.getResourceAsStream(resource)) {
            if (in == null) {
                throw new IllegalArgumentException("Test data file not found on classpath: " + resource);
            }
            return MAPPER.readTree(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse test data file: " + resource, e);
        }
    }

    public static JsonNode entry(String file, String key) {
        JsonNode entry = json(file).get(key);
        if (entry == null) {
            throw new IllegalArgumentException("Key '" + key + "' not found in " + file + ".json");
        }
        return entry;
    }
}
