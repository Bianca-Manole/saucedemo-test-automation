package testdata.classes;

import com.saucedemo.utils.Reader;
import org.json.simple.JSONObject;

public class Product {
    private final String name;
    private final String description;
    private final String price;

    public Product(String productKey) {
        JSONObject product = Reader.entry("products", productKey);
        this.name = product.get("name").toString();
        this.description = product.get("description").toString();
        this.price = product.get("price").toString();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
}
