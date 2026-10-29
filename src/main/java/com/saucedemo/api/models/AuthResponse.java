package com.saucedemo.api.models;

// Response body of POST /auth: a token on success, a reason on failure.
public record AuthResponse(String token, String reason) {
}
