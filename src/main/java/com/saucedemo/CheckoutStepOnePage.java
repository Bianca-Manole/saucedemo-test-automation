package com.saucedemo;

import org.openqa.selenium.WebDriver;
import testdata.classes.CheckoutUser;
import testdata.pages.Checkout;
import testdata.pages.Inventory;

public class CheckoutStepOnePage {
    WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoText() {
        return driver.findElement(Inventory.LOGO).getText();
    }

    public String getTitleText() {
        return driver.findElement(Checkout.CHECKOUT_TITLE_ONE).getText();
    }

    public boolean isCheckoutFormDisplayed() {
        return  driver.findElement(Checkout.CHECKOUT_FORM).isDisplayed();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(Checkout.FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(Checkout.LAST_NAME_INPUT).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(Checkout.POSTAL_CODE_INPUT).sendKeys(postalCode);
    }

    public boolean isContinueButtonDisplayed() {
        return driver.findElement(Checkout.CONTINUE_BUTTON).isDisplayed();
    }

    public void clickContinue() {
        driver.findElement(Checkout.CONTINUE_BUTTON).click();
    }

    public boolean isCancelButtonDisplayed() {
        return driver.findElement(Checkout.CANCEL_BUTTON).isDisplayed();
    }

    public void clickCancel() {
        driver.findElement(Checkout.CANCEL_BUTTON).click();
    }
    public boolean hasError() {
        return !driver.findElements(Checkout.ERROR).isEmpty();
    }

    public void enterDataCheckout(CheckoutUser checkoutUser) {
        enterFirstName(checkoutUser.getFirstName());
        enterLastName(checkoutUser.getLastName());
        enterPostalCode(checkoutUser.getZip());
        clickContinue();
    }
}
