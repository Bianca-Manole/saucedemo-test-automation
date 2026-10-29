package testdata.classes;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.Reader;
import org.json.simple.JSONObject;

public class User {
    private final String username;
    private final String password;

    public User(String userType) {
        JSONObject user = Reader.entry("users", userType);
        this.username = user.get("username").toString();
        this.password = user.get("password").toString();
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
