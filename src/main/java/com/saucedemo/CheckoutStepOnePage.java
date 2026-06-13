package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.classes.CheckoutUser;

public class CheckoutStepOnePage {
    WebDriver driver;

    private static final By CHECKOUT_TITLE_ONE = By.className("title");
    private static final By CHECKOUT_FORM = By.className("checkout_info");
    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By POSTAL_CODE_INPUT = By.id("postal-code");
    private static final By CANCEL_BUTTON = By.id("cancel");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(CHECKOUT_TITLE_ONE).getText();
    }

    public boolean isCheckoutFormDisplayed() {
        return driver.findElement(CHECKOUT_FORM).isDisplayed();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE_INPUT).sendKeys(postalCode);
    }

    public boolean isContinueButtonDisplayed() {
        return driver.findElement(CONTINUE_BUTTON).isDisplayed();
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public boolean isCancelButtonDisplayed() {
        return driver.findElement(CANCEL_BUTTON).isDisplayed();
    }

    public boolean hasError() {
        return !driver.findElements(ERROR).isEmpty();
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }

    public void enterDataCheckout(CheckoutUser checkoutUser) {
        enterFirstName(checkoutUser.getFirstName());
        enterLastName(checkoutUser.getLastName());
        enterPostalCode(checkoutUser.getZip());
        clickContinue();
    }
}
