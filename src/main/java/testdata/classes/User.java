package testdata.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.Reader;

public class User {
    private final String username;
    private final String password;

    public User(String userType) {
        JsonNode user = Reader.entry("users", userType);
        this.username = user.required("username").asText();
        this.password = user.required("password").asText();
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User fromConfig() {
        return new User(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
