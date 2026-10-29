package com.saucedemo.api.clients;

import com.saucedemo.api.ApiClient;
import com.saucedemo.api.models.Credentials;
import io.restassured.response.Response;

public class AuthClient extends ApiClient {
    private static final String AUTH = "/auth";

    public Response createToken(Credentials credentials) {
        return request().body(credentials).post(AUTH);
    }
}
