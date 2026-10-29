package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final String PATH = "cart.html";

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By ITEM_QUANTITY = By.cssSelector("[data-test='item-quantity']");
    private static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    private static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By REMOVE_BUTTON = By.cssSelector("button[id^='remove']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrl(PATH);
    }

    public String getTitleText() {
        return textOf(TITLE);
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

    public String getItemQuantity() {
        return textOf(ITEM_QUANTITY);
    }

    public void removeItem() {
        click(REMOVE_BUTTON);
    }

    public boolean isEmpty() {
        return isGone(ITEM_NAME);
    }

    public void continueShopping() {
        click(CONTINUE_SHOPPING_BUTTON);
    }

    public void checkout() {
        click(CHECKOUT_BUTTON);
    }

    public boolean isCheckoutButtonVisible() {
        return isVisible(CHECKOUT_BUTTON);
    }

    public boolean isContinueShoppingButtonVisible() {
        return isVisible(CONTINUE_SHOPPING_BUTTON);
    }
}
