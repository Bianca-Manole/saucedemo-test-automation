package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.classes.User;

public class LoginPage extends BasePage {
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isAtUrl("") && isVisible(LOGIN_BUTTON);
    }

    public void login(User user) {
        type(USERNAME_INPUT, user.getUsername());
        type(PASSWORD_INPUT, user.getPassword());
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return textOf(ERROR);
    }
}
