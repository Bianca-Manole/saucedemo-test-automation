package com.saucedemo;

import org.openqa.selenium.WebDriver;
import testdata.pages.Inventory;
import testdata.pages.Item;

public class ItemPage {

    WebDriver driver;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getLogoText() {
        return driver.findElement(Inventory.LOGO).getText();
    }

    public void clickBackToProducts() {
        driver.findElement(Item.BACK_TO_PRODUCTS).click();
    }

    public void clickAddToCart() {
        driver.findElement(Item.ADD_TO_CART).click();
    }

    public void clickCartButton() {
        driver.findElement(Inventory.CART_BUTTON_EMPTY).click();
    }

    public String getItemName() {
        return driver.findElement(Inventory.ITEM_NAME).getText();
    }

    public String getItemDescription(){
        return driver. findElement(Inventory.ITEM_DESCRIPTION).getText();
    }
    public String getItemPrice() {
        return driver.findElement(Inventory.ITEM_PRICE).getText();
    }
}
