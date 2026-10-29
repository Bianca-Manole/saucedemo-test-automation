package com.saucedemo.tests.ui;

import com.saucedemo.base.BaseTest;
import com.saucedemo.testdata.classes.User;
import com.saucedemo.testdata.pages.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest {

    @Test
    public void standardUserIsRedirectedToInventory() {
        loginPage.login(standardUser);

        Assertions.assertAll(
                () -> Assertions.assertTrue(inventoryPage.isOpen(), "Standard user should land on the inventory page after login"),
                () -> Assertions.assertTrue(inventoryPage.isProductListVisible(), "Product list should be visible after login"));
    }

    @Test
    public void lockedOutUserSeesLockedOutError() {
        loginPage.login(new User("locked"));

        Assertions.assertEquals(Login.LOCKED_OUT_USER_ERROR, loginPage.getErrorMessage(),
                "Locked out user should see the locked out error");
    }

    @Test
    public void invalidCredentialsShowError() {
        loginPage.login(new User("invalidCredentials"));

        Assertions.assertEquals(Login.INVALID_USERNAME_PASSWORD, loginPage.getErrorMessage(),
                "Invalid credentials should show the username/password mismatch error");
    }

    @Test
    public void menuShowsLogoutLinkForLoggedInUser() {
        loginAsStandardUser();
        header.openMenu();

        Assertions.assertTrue(header.isLogoutLinkVisible(), "Logout link should be visible in the menu");
    }

    @Test
    public void logoutReturnsToLoginPage() {
        loginAsStandardUser();
        header.logout();

        Assertions.assertTrue(loginPage.isOpen(), "User should be back on the login page after logout");
    }
}
