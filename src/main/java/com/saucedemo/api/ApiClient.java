package com.saucedemo.api;

import com.saucedemo.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

// API equivalent of BasePage: every client builds its requests from here.
public abstract class ApiClient {
    private final String baseUrl;

    protected ApiClient(String baseUrlConfigKey) {
        this.baseUrl = ConfigReader.get(baseUrlConfigKey);
    }

    protected RequestSpecification request() {
        return RestAssured.given().baseUri(baseUrl);
    }

    protected RequestSpecification jsonRequest() {
        return request()
                .contentType(ContentType.JSON)
                // ContentType.JSON would send four media types; Restful Booker answers 418 to anything but this one.
                .accept("application/json");
    }

    // A null token sends the request without authentication.
    protected RequestSpecification jsonRequest(String token) {
        RequestSpecification request = jsonRequest();
        return token == null ? request : request.cookie("token", token);
    }
}
