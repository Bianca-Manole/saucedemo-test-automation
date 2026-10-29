package testdata.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.utils.Reader;

public class Product {
    private final String name;
    private final String description;
    private final String price;

    public Product(String productKey) {
        JsonNode product = Reader.entry("products", productKey);
        this.name = product.required("name").asText();
        this.description = product.required("description").asText();
        this.price = product.required("price").asText();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
}
