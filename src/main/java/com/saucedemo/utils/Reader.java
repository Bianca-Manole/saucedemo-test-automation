package com.saucedemo.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Reader {
    private static final String TEST_DATA_DIR = "/testdata/";

    private Reader() {
    }

    public static JSONObject json(String name) {
        String resource = TEST_DATA_DIR + name + ".json";
        try (InputStream in = Reader.class.getResourceAsStream(resource)) {
            if (in == null) {
                throw new IllegalArgumentException("Test data file not found on classpath: " + resource);
            }
            return (JSONObject) new JSONParser().parse(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Could not parse test data file: " + resource, e);
        }
    }

    public static JSONObject entry(String file, String key) {
        JSONObject entry = (JSONObject) json(file).get(key);
        if (entry == null) {
            throw new IllegalArgumentException("Key '" + key + "' not found in " + file + ".json");
        }
        return entry;
    }
}
