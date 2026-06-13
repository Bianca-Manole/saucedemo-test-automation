package com.saucedemo;

import com.saucedemo.components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testdata.URL;
import testdata.pages.Login;

public class LoginTests extends BaseTest {

    @Test
    public void loginStandardUser() {
        loginPage.authentication(standardUser);
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_INVENTORY, driver.getCurrentUrl());
        Assertions.assertEquals(Header.LOGO_TEXT, header.getLogoText());
        Assertions.assertTrue(inventoryPage.isItemListDisplayed());
        Assertions.assertTrue(header.isLogoutButtonDisplayed());
    }

    @Test
    public void loginLockedUser() {
        loginPage.authentication(lockedUser);
        Assertions.assertEquals(Login.LOCKED_OUT_USER_ERROR, loginPage.getError());
    }

    @Test
    public void loginInvalidUser() {
        loginPage.authentication(invalidUser);
        Assertions.assertEquals(Login.INVALID_USERNAME_PASSWORD, loginPage.getError());
    }

    @Test
    public void logoutStandardUser() {
        loginPage.authentication(standardUser);
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_INVENTORY, driver.getCurrentUrl());
        Assertions.assertEquals(Header.LOGO_TEXT, header.getLogoText());
        header.clickMenu();
        header.clickLogout();
        Assertions.assertEquals(URL.MAIN, driver.getCurrentUrl());
        Assertions.assertEquals(Header.LOGO_TEXT, loginPage.getLogoText());
    }
}
