package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {
    private static final String PATH = "checkout-step-two.html";

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    private static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By PAYMENT_INFORMATION = By.cssSelector("[data-test='payment-info-label']");
    private static final By PAYMENT_METHOD = By.cssSelector("[data-test='payment-info-value']");
    private static final By SHIPPING_METHOD = By.cssSelector("[data-test='shipping-info-value']");
    private static final By ITEM_TOTAL = By.cssSelector("[data-test='subtotal-label']");
    private static final By TAX = By.cssSelector("[data-test='tax-label']");
    private static final By TOTAL = By.cssSelector("[data-test='total-label']");
    private static final By CANCEL_BUTTON = By.id("cancel");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
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

    public boolean isPaymentInformationVisible() {
        return isVisible(PAYMENT_INFORMATION);
    }

    public String getPaymentMethod() {
        return textOf(PAYMENT_METHOD);
    }

    public String getShippingMethod() {
        return textOf(SHIPPING_METHOD);
    }

    public double getItemTotal() {
        return amountOf(ITEM_TOTAL);
    }

    public double getTax() {
        return amountOf(TAX);
    }

    public double getTotal() {
        return amountOf(TOTAL);
    }

    public boolean isFinishButtonVisible() {
        return isVisible(FINISH_BUTTON);
    }

    public boolean isCancelButtonVisible() {
        return isVisible(CANCEL_BUTTON);
    }

    public void finish() {
        click(FINISH_BUTTON);
    }

    private double amountOf(By locator) {
        String text = textOf(locator);
        return Double.parseDouble(text.substring(text.indexOf('$') + 1));
    }
}
