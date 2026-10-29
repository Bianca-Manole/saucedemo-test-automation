package com.saucedemo.pages.components;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By CART_LINK = By.id("shopping_cart_container");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    public Header(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        click(MENU_BUTTON);
    }

    public boolean isLogoutLinkVisible() {
        return isVisible(LOGOUT_LINK);
    }

    public void logout() {
        openMenu();
        click(LOGOUT_LINK);
    }

    public void openCart() {
        click(CART_LINK);
    }

    public boolean isCartBadgeVisible() {
        return isVisible(CART_BADGE);
    }

    public String getCartBadgeCount() {
        return textOf(CART_BADGE);
    }
}
