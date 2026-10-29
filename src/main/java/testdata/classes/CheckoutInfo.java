package testdata.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.utils.Reader;

public class CheckoutInfo {
    private final String firstName;
    private final String lastName;
    private final String zip;

    public CheckoutInfo(String key) {
        JsonNode info = Reader.entry("checkout", key);
        this.firstName = info.required("firstName").asText();
        this.lastName = info.required("lastName").asText();
        this.zip = info.required("zip").asText();
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
