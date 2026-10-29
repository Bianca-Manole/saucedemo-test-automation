package com.saucedemo.api;

import com.saucedemo.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

// API equivalent of BasePage: every client builds its requests from here.
public abstract class ApiClient {

    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(ConfigReader.get("apiBaseUrl"))
                .contentType(ContentType.JSON)
                // ContentType.JSON would send four media types; Restful Booker answers 418 to anything but this one.
                .accept("application/json");
    }

    // A null token sends the request without authentication.
    protected RequestSpecification request(String token) {
        RequestSpecification request = request();
        return token == null ? request : request.cookie("token", token);
    }
}
