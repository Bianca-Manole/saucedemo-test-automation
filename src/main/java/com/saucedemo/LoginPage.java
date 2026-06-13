package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testdata.classes.User;
import testdata.pages.Login;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }


    public void enterUsername(String username) {
        driver.findElement(Login.USERNAME_INPUT).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(Login.PASSWORD_INPUT).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(Login.LOGIN_BUTTON).click();
    }

    public String getLogoText() {
        return driver.findElement(Login.LOGO).getText();
    }

    public String getError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Login.ERROR));
        return driver.findElement(Login.ERROR).getText();
    }

    public void authentication(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
    }
}
