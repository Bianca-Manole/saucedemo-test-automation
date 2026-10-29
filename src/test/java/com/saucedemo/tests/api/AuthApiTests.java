package com.saucedemo.tests.api;

import com.saucedemo.api.models.AuthResponse;
import com.saucedemo.api.models.Credentials;
import com.saucedemo.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthApiTests extends BaseApiTest {

    @Test
    public void validCredentialsReturnToken() {
        Response response = authClient.createToken(Credentials.fromConfig());

        Assertions.assertEquals(200, response.statusCode(), "Auth with valid credentials should return 200");
        AuthResponse body = response.as(AuthResponse.class);
        Assertions.assertNotNull(body.token(), "Response should contain a token");
        Assertions.assertFalse(body.token().isBlank(), "Token should not be blank");
    }

    @Test
    public void invalidCredentialsAreRejected() {
        Response response = authClient.createToken(new Credentials("admin", "wrong-password"));

        // Restful Booker answers 200 with a reason instead of 401.
        Assertions.assertEquals(200, response.statusCode(), "Auth endpoint returns 200 even for bad credentials");
        AuthResponse body = response.as(AuthResponse.class);
        Assertions.assertNull(body.token(), "No token should be issued for bad credentials");
        Assertions.assertEquals("Bad credentials", body.reason(), "Response should explain why auth failed");
    }
}
