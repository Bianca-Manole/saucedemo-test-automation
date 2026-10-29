package com.saucedemo.api.clients;

import com.saucedemo.api.ApiClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testdata.classes.User;

import java.util.Map;

// HTTP client for saucedemo itself. The site is a static single-page app: the server only serves files
// (GET) and refuses any data sent to it (POST/PUT/DELETE), because login and cart run in the browser.
public class SauceDemoClient extends ApiClient {
    private static final String HOME = "/";
    private static final String MANIFEST = "/manifest.json";
    private static final String ROBOTS = "/robots.txt";

    public SauceDemoClient() {
        super("baseUrl");
    }

    public Response getHomePage() {
        return request().get(HOME);
    }

    public Response getManifest() {
        return request().accept(ContentType.JSON).get(MANIFEST);
    }

    public Response getRobotsTxt() {
        return request().get(ROBOTS);
    }

    public Response getResource(String path) {
        return request().get(path);
    }

    public Response postLogin(User user) {
        return post(HOME, Map.of("username", user.getUsername(), "password", user.getPassword()));
    }

    public Response post(String path, Object body) {
        return request().contentType(ContentType.JSON).body(body).post(path);
    }
}
