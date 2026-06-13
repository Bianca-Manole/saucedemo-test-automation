package testdata.classes;

import com.saucedemo.utils.Reader;
import org.json.simple.JSONObject;

public class User {
    private final String username;
    private final String password;

    public User(String userType){
        JSONObject user = (JSONObject) Reader.json("users").get(userType);

        this.username = user.get("username").toString();
        this.password = user.get("password").toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
