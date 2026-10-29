package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private static final String PATH = "checkout-complete.html";

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By COMPLETE_HEADER = By.cssSelector("[data-test='complete-header']");
    private static final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrl(PATH);
    }

    public String getTitleText() {
        return textOf(TITLE);
    }

    public String getCompleteHeader() {
        return textOf(COMPLETE_HEADER);
    }

    public boolean isBackHomeButtonVisible() {
        return isVisible(BACK_HOME_BUTTON);
    }
}
