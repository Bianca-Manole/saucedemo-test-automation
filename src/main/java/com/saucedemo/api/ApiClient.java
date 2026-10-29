package com.saucedemo.api;

import com.saucedemo.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

// API equivalent of BasePage: every client builds its requests from here.
public abstract class ApiClient {

    protected RequestSpecification request() {
        return RestAssured.given().baseUri(ConfigReader.get("baseUrl"));
    }
}
