package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.classes.CheckoutInfo;

public class CheckoutStepOnePage extends BasePage {
    private static final String PATH = "checkout-step-one.html";

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By CHECKOUT_FORM = By.className("checkout_info");
    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By POSTAL_CODE_INPUT = By.id("postal-code");
    private static final By CANCEL_BUTTON = By.id("cancel");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrl(PATH);
    }

    public String getTitleText() {
        return textOf(TITLE);
    }

    public boolean isCheckoutFormVisible() {
        return isVisible(CHECKOUT_FORM);
    }

    public boolean isContinueButtonVisible() {
        return isVisible(CONTINUE_BUTTON);
    }

    public boolean isCancelButtonVisible() {
        return isVisible(CANCEL_BUTTON);
    }

    public void submitInformation(CheckoutInfo info) {
        type(FIRST_NAME_INPUT, info.getFirstName());
        type(LAST_NAME_INPUT, info.getLastName());
        type(POSTAL_CODE_INPUT, info.getZip());
        click(CONTINUE_BUTTON);
    }

    public String getErrorMessage() {
        return textOf(ERROR);
    }
}
