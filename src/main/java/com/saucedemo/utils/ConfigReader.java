package com.saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPS = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(CONFIG_FILE + " not found in the project root", e);
        }
        return properties;
    }

    public static String get(String key) {
        String value = PROPS.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing key '" + key + "' in " + CONFIG_FILE);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
