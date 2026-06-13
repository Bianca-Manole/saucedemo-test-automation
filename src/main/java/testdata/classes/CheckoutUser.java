package testdata.classes;

import com.saucedemo.utils.Reader;
import org.json.simple.JSONObject;

public class CheckoutUser {
    private String firstName;
    private String lastName;
    private String zip;

    public CheckoutUser(String userType) {
        JSONObject user = (JSONObject) Reader.json("checkout").get(userType);
        this.firstName = user.get("firstName").toString();
        this.lastName = user.get("lastName").toString();
        this.zip = user.get("zip").toString();
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }
    
    public String getZip() {
        return zip;
    }
}
