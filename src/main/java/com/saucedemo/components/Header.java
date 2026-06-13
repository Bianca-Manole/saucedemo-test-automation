package com.saucedemo.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    public static final String LOGO_TEXT = "Swag Labs";

    WebDriver driver;

    private static final By LOGO = By.xpath("//div[contains(text(), 'Swag Labs')]");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_BUTTON = By.id("logout_sidebar_link");
    private static final By CART_BUTTON = By.id("shopping_cart_container");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoText() {
        return driver.findElement(LOGO).getText();
    }

    public void clickMenu() {
        driver.findElement(MENU_BUTTON).click();
    }

    public void clickLogout() {
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public boolean isLogoutButtonDisplayed() {
        clickMenu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        return driver.findElement(LOGOUT_BUTTON).isDisplayed();
    }

    public void clickCartButton() {
        driver.findElement(CART_BUTTON).click();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }
}
