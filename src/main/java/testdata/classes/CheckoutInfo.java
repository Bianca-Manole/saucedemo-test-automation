package testdata.classes;

import com.saucedemo.utils.Reader;
import org.json.simple.JSONObject;

public class CheckoutInfo {
    private final String firstName;
    private final String lastName;
    private final String zip;

    public CheckoutInfo(String key) {
        JSONObject info = Reader.entry("checkout", key);
        this.firstName = info.get("firstName").toString();
        this.lastName = info.get("lastName").toString();
        this.zip = info.get("zip").toString();
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
