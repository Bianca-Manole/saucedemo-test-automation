package com.saucedemo.api.models;

import com.saucedemo.utils.ConfigReader;

public record Credentials(String username, String password) {

    public static Credentials fromConfig() {
        return new Credentials(ConfigReader.get("apiUsername"), ConfigReader.get("apiPassword"));
    }
}
