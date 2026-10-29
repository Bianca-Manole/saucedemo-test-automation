package com.saucedemo.tests.api;

import com.saucedemo.api.clients.SauceDemoClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testdata.classes.User;

import java.util.Map;

// saucedemo has no backend: login and cart run in the browser. These tests prove the server
// refuses data sent to it, which is why the UI tests are the only way to test login and cart.
public class SauceDemoPostApiTests {
    private static final int METHOD_NOT_ALLOWED = 405;

    private final SauceDemoClient sauceDemo = new SauceDemoClient();

    @Test
    public void postingLoginCredentialsIsNotAllowed() {
        Response response = sauceDemo.postLogin(User.fromConfig());

        Assertions.assertEquals(METHOD_NOT_ALLOWED, response.statusCode(),
                "Server should refuse login data: login is handled in the browser");
    }

    @Test
    public void refusedPostReturnsErrorPageWithoutSessionCookie() {
        Response response = sauceDemo.postLogin(User.fromConfig());

        Assertions.assertAll(
                () -> Assertions.assertTrue(response.asString().contains("405 Not Allowed"), "Response should be the 405 error page"),
                () -> Assertions.assertNull(response.getCookie("session-username"), "Server should not start a session"));
    }

    @Test
    public void postingCartContentsIsNotAllowed() {
        Response response = sauceDemo.post("/cart.html", Map.of("cart-contents", new int[]{4}));

        Assertions.assertEquals(METHOD_NOT_ALLOWED, response.statusCode(),
                "Server should refuse cart data: the cart is stored in the browser");
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/inventory.html", "/manifest.json"})
    public void postingToAnyPathIsNotAllowed(String path) {
        Response response = sauceDemo.post(path, Map.of("test", "data"));

        Assertions.assertEquals(METHOD_NOT_ALLOWED, response.statusCode(), "POST " + path + " should return 405");
    }
}
