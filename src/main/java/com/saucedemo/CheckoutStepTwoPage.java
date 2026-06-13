package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    WebDriver driver;

    private static final By CHECKOUT_TITLE_TWO = By.cssSelector("[data-test='title']");
    private static final By CHECKOUT_COMPLETE_TITLE = By.className("title");
    private static final By COMPLETE_MESSAGE = By.className("complete-header");
    private static final By CANCEL_BUTTON = By.id("cancel");
    private static final By PAYMENT_INFORMATION = By.cssSelector("[data-test='payment-info-label']");
    private static final By ITEM_TOTAL = By.cssSelector("[data-test='subtotal-label']");
    private static final By TAX = By.cssSelector("[data-test='tax-label']");
    private static final By TOTAL = By.cssSelector("[data-test='total-label']");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By PAYMENT_METHOD = By.cssSelector("[data-test='payment-info-value']");
    private static final By SHIPPING_METHOD = By.cssSelector("[data-test='shipping-info-value']");
    private static final By BACK_HOME_BUTTON = By.id("back-to-products");
    private static final By CART_ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By CART_ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By CART_ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(CHECKOUT_TITLE_TWO).getText();
    }

    public boolean isFinishButtonDisplayed() {
        return driver.findElement(FINISH_BUTTON).isDisplayed();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public boolean isCancelButtonDisplayed() {
        return driver.findElement(CANCEL_BUTTON).isDisplayed();
    }

    public String getCheckoutItemName() {
        return driver.findElement(CART_ITEM_NAME).getText();
    }

    public String getCheckoutItemPrice() {
        return driver.findElement(CART_ITEM_PRICE).getText();
    }

    public String getCheckoutItemDescription() {
        return driver.findElement(CART_ITEM_DESCRIPTION).getText();
    }

    public boolean isPaymentInformationDisplayed() {
        return driver.findElement(PAYMENT_INFORMATION).isDisplayed();
    }

    public String getPaymentMethodText() {
        return driver.findElement(PAYMENT_METHOD).getText();
    }

    public String getShippingMethodText() {
        return driver.findElement(SHIPPING_METHOD).getText();
    }

    public boolean isTotalCorrect() {
        double itemTotal = Double.parseDouble(
                driver.findElement(ITEM_TOTAL).getText()
                        .replace("Item total: $", ""));
        double tax = Double.parseDouble(
                driver.findElement(TAX).getText()
                        .replace("Tax: $", ""));
        double total = Double.parseDouble(
                driver.findElement(TOTAL).getText()
                        .replace("Total: $", ""));

        return Math.round((itemTotal + tax) * 100.0) / 100.0 == total;
    }

    public String getCheckoutCompleteText() {
        return driver.findElement(CHECKOUT_COMPLETE_TITLE).getText();
    }

    public String getCompleteOrderTitle() {
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }

    public boolean isBackHomeButtonDisplayed() {
        return driver.findElement(BACK_HOME_BUTTON).isDisplayed();
    }
}
