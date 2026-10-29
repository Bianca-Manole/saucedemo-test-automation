package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends BasePage {
    private static final String PATH = "inventory-item.html?id=";

    private static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    private static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By ADD_TO_CART_BUTTON = By.id("add-to-cart");
    private static final By REMOVE_BUTTON = By.id("remove");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrlStartingWith(PATH);
    }

    public void addToCart() {
        click(ADD_TO_CART_BUTTON);
    }

    public boolean isRemoveButtonVisible() {
        return isVisible(REMOVE_BUTTON);
    }

    public String getItemName() {
        return textOf(ITEM_NAME);
    }

    public String getItemDescription() {
        return textOf(ITEM_DESCRIPTION);
    }

    public String getItemPrice() {
        return textOf(ITEM_PRICE);
    }
}
