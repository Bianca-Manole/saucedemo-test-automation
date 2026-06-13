package com.saucedemo;

import org.openqa.selenium.WebDriver;
import testdata.pages.Cart;
import testdata.pages.Inventory;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoText() {
        return driver.findElement(Inventory.LOGO).getText();
    }

    public void clickContinueShopping() {
        driver.findElement(Cart.CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickRemove() {
        driver.findElement(Cart.REMOVE_BUTTON).click();
    }

    public void clickCheckout() {
        driver.findElement(Cart.CHECKOUT_BUTTON).click();
    }
    public boolean isCheckOutButtonDisplayed() {
        return driver.findElement(Cart.CHECKOUT_BUTTON).isDisplayed();
    }

    public boolean isContinueShoppingButtonDisplayed() {
        return driver.findElement(Cart.CONTINUE_SHOPPING_BUTTON).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(Cart.TITLE).getText();
    }

    public String getItemInCartName() {
        return driver.findElement(Cart.CART_ITEM_NAME).getText();
    }

    public String getItemPriceInCart() {
        return driver.findElement(Cart.CART_ITEM_PRICE).getText();
    }

    public String getItemDescriptionInCart() {
        return driver.findElement(Cart.CART_ITEM_DESCRIPTION).getText();
    }
}
