package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage {
    WebDriver driver;

    private static final By ADD_TO_CART = By.id("add-to-cart");
    private static final By REMOVE_FROM_CART = By.id("remove");

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        driver.findElement(ADD_TO_CART).click();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(REMOVE_FROM_CART).isDisplayed();
    }

    public String getItemName() {
        return driver.findElement(InventoryPage.ITEM_NAME).getText();
    }

    public String getItemDescription() {
        return driver.findElement(InventoryPage.ITEM_DESCRIPTION).getText();
    }

    public String getItemPrice() {
        return driver.findElement(InventoryPage.ITEM_PRICE).getText();
    }
}
