package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    private static final By TITLE = By.className("title");
    private static final By ITEM_QUANTITY = By.cssSelector("[data-test='item-quantity']");
    private static final By CART_ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By CART_ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    private static final By CART_ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By REMOVE_BUTTON = By.cssSelector("button[id^='remove']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.cssSelector("button[id^='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickRemove() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public boolean isCheckOutButtonDisplayed() {
        return driver.findElement(CHECKOUT_BUTTON).isDisplayed();
    }

    public boolean isContinueShoppingButtonDisplayed() {
        return driver.findElement(CONTINUE_SHOPPING_BUTTON).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(TITLE).getText();
    }

    public String getItemInCartName() {
        return driver.findElement(CART_ITEM_NAME).getText();
    }

    public String getItemPriceInCart() {
        return driver.findElement(CART_ITEM_PRICE).getText();
    }

    public String getItemDescriptionInCart() {
        return driver.findElement(CART_ITEM_DESCRIPTION).getText();
    }

    public boolean isCartEmpty() {
        return driver.findElements(CART_ITEM_NAME).isEmpty();
    }

    public String getItemQuantityInCart() {
        return driver.findElement(ITEM_QUANTITY).getText();
    }
}
