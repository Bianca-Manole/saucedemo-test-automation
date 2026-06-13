package com.saucedemo;

import org.openqa.selenium.WebDriver;
import testdata.pages.Cart;
import testdata.pages.Checkout;
import testdata.pages.Inventory;

public class CheckoutStepTwoPage {
    WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(Checkout.CHECKOUT_TITLE_TWO).getText();
    }

    public boolean isFinishButtonDisplayed() {
        return driver.findElement(Checkout.FINISH_BUTTON).isDisplayed();
    }

    public void clickFinishButton() {
        driver.findElement(Checkout.FINISH_BUTTON).click();
    }

    public boolean isCancelButtonDisplayed() {
        return driver.findElement(Checkout.CANCEL_BUTTON).isDisplayed();
    }

    public String getCheckOutInfoErrorText() {
        return driver.findElement(Checkout.ERROR).getText();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(Checkout.ERROR).isDisplayed();
    }


    public String getCheckoutItemName() {
        return driver.findElement(Cart.CART_ITEM_NAME).getText();
    }

    public String getCheckoutItemPrice() {
        return driver.findElement(Cart.CART_ITEM_PRICE).getText();
    }

    public String getCheckoutItemDescription() {
        return driver.findElement(Cart.CART_ITEM_DESCRIPTION).getText();
    }

    public boolean isPaymentInformationDisplayed() {
        return driver.findElement(Checkout.PAYMENT_INFORMATION).isDisplayed();
    }

    public String getPaymentInformationText() {
        return driver.findElement(Checkout.PAYMENT_INFORMATION).getText();
    }

    public boolean isPaymentMethodDisplayed() {
        return driver.findElement(Checkout.PAYMENT_METHOD).isDisplayed();
    }

    public String getPaymentMethodText() {
        return driver.findElement(Checkout.PAYMENT_METHOD).getText();
    }

    public boolean isShippingMethodDisplayed() {
        return driver.findElement(Checkout.SHIPPING_METHOD).isDisplayed();
    }

    public String getShippingMethodText() {
        return driver.findElement(Checkout.SHIPPING_METHOD).getText();
    }

    public boolean isItemTotalDisplayed() {
        return driver.findElement(Checkout.ITEM_TOTAL).isDisplayed();
    }

    public String getItemTotalText() {
        return driver.findElement(Checkout.ITEM_TOTAL).getText();
    }

    public boolean isTaxDisplayed() {
        return driver.findElement(Checkout.TAX).isDisplayed();
    }

    public String getTaxText() {
        return driver.findElement(Checkout.TAX).getText();
    }

    public boolean isTotalPriceDisplayed() {
        return driver.findElement(Checkout.TOTAL).isDisplayed();
    }

    public String getTotalFromPage() {
        return driver.findElement(Checkout.TOTAL).getText();
    }

    public boolean isTotalCorrect() {
        double itemTotal = Double.parseDouble(
                driver.findElement(Checkout.ITEM_TOTAL).getText()
                        .replace("Item total: $", ""));
        double tax = Double.parseDouble(
                driver.findElement(Checkout.TAX).getText()
                        .replace("Tax: $", ""));
        double total = Double.parseDouble(
                driver.findElement(Checkout.TOTAL).getText()
                        .replace("Total: $", ""));

        return Math.round((itemTotal + tax) * 100.0) / 100.0 == total;
    }

    public String getCheckoutCompleteText() {
        return driver.findElement(Checkout.CHECKOUT_COMPLETE_TITLE).getText();
    }

    public String getCompleteOrderTitle() {
        return driver.findElement(Checkout.COMPLETE_MESSAGE).getText();
    }

    public boolean isBackHomeButtonDisplayed() {
        return driver.findElement(Checkout.BACK_HOME_BUTTON).isDisplayed();
    }



}
