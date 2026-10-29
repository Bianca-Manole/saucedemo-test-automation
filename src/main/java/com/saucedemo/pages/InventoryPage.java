package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class InventoryPage extends BasePage {
    private static final String PATH = "inventory.html";

    private static final By ITEM_LIST = By.className("inventory_list");
    private static final By SORT_DROPDOWN = By.className("product_sort_container");
    private static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrl(PATH);
    }

    public boolean isProductListVisible() {
        return isVisible(ITEM_LIST);
    }

    public void openProduct(String productName) {
        click(By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']"));
    }

    public void addToCart(String productName) {
        click(addToCartButton(productName));
    }

    public boolean isAddToCartButtonVisible(String productName) {
        return isVisible(addToCartButton(productName));
    }

    public boolean isRemoveButtonVisible(String productName) {
        return isVisible(removeButton(productName));
    }

    public void sortBy(String sortOption) {
        selectByValue(SORT_DROPDOWN, sortOption);
    }

    public List<String> getProductNames() {
        return textsOf(ITEM_NAME);
    }

    public List<Double> getProductPrices() {
        return textsOf(ITEM_PRICE).stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    private static By addToCartButton(String productName) {
        return By.id("add-to-cart-" + toButtonId(productName));
    }

    private static By removeButton(String productName) {
        return By.id("remove-" + toButtonId(productName));
    }

    private static String toButtonId(String productName) {
        return productName.toLowerCase().replace(" ", "-");
    }
}
