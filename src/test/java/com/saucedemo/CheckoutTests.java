package com.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.URL;
import testdata.classes.CheckoutUser;
import testdata.pages.Checkout;

public class CheckoutTests extends BaseTest {

    CheckoutUser validCheckoutInfo = new CheckoutUser("valid");
    CheckoutUser emptyFirstNameCheckoutInfo = new CheckoutUser("emptyFirstName");
    CheckoutUser emptyLastNameCheckoutInfo = new CheckoutUser("emptyLastName");
    CheckoutUser emptyZipCheckoutInfo = new CheckoutUser("emptyZip");
    CheckoutUser firstNameMaxLengthCheckoutInfo = new CheckoutUser("firstNameMaxLength");
    CheckoutUser firstNameBelowMaxLengthCheckoutInfo = new CheckoutUser("firstNameBelowMaxLength");
    CheckoutUser firstNameOverLimitCheckoutInfo = new CheckoutUser("firstNameOverLimit");
    CheckoutUser zipMaxLengthCheckoutInfo = new CheckoutUser("zipMaxLength");
    CheckoutUser zipOverLimitCheckoutInfo = new CheckoutUser("zipOverLimit");
    CheckoutUser zipAlphaCharactersCheckoutInfo = new CheckoutUser("zipAlphaCharacters");

    @BeforeEach
    @Override
    public void beforeTests() {
        super.beforeTests();
        loginPage.authentication(standardUser);
        inventoryPage.addToCart(InventoryPage.BACKPACK_ADD_TO_CART_BUTTON);
        header.clickCartButton();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CART, driver.getCurrentUrl());
        cartPage.clickCheckout();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CHECKOUT_STEP_ONE, driver.getCurrentUrl());
        Assertions.assertTrue(checkoutStepOnePage.isCheckoutFormDisplayed());
    }

    @Test
    public void checkCheckoutButton() {
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_ONE_TEXT, checkoutStepOnePage.getTitleText());
        Assertions.assertTrue(checkoutStepOnePage.isCheckoutFormDisplayed());
        Assertions.assertTrue(checkoutStepOnePage.isContinueButtonDisplayed());
        Assertions.assertTrue(checkoutStepOnePage.isCancelButtonDisplayed());
    }

    @Test
    public void enterValidCheckoutData() {
        checkoutStepOnePage.enterDataCheckout(validCheckoutInfo);
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed());
        Assertions.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed());
    }

    @Test
    public void enterEmptyFirstNameCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(emptyFirstNameCheckoutInfo);
        Assertions.assertEquals(Checkout.ERROR_FIRST_NAME_TEXT, checkoutStepOnePage.getErrorText());
    }

    @Test
    public void enterEmptyLastNameCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(emptyLastNameCheckoutInfo);
        Assertions.assertEquals(Checkout.ERROR_LAST_NAME_TEXT, checkoutStepOnePage.getErrorText());
    }

    @Test
    public void enterEmptyZipCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(emptyZipCheckoutInfo);
        Assertions.assertEquals(Checkout.ERROR_POSTAL_CODE_TEXT, checkoutStepOnePage.getErrorText());
    }

    @Test
    public void enterFirstNameMaxLengthCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(firstNameMaxLengthCheckoutInfo);
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed());
        Assertions.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed());
    }

    @Test
    public void enterFirstNameBelowLengthCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(firstNameBelowMaxLengthCheckoutInfo);
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed());
        Assertions.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed());
    }

    @Test
    public void enterFirstNameOverLimitCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(firstNameOverLimitCheckoutInfo);
        Assertions.assertTrue(checkoutStepOnePage.hasError());
    }

    @Test
    public void enterZipMaxLengthCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(zipMaxLengthCheckoutInfo);
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertTrue(checkoutStepTwoPage.isFinishButtonDisplayed());
        Assertions.assertTrue(checkoutStepTwoPage.isCancelButtonDisplayed());
    }

    @Test
    public void enterZipOverLimitCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(zipOverLimitCheckoutInfo);
        Assertions.assertTrue(checkoutStepOnePage.hasError());
    }

    @Test
    public void enterZipAlphaCharactersCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(zipAlphaCharactersCheckoutInfo);
        Assertions.assertTrue(checkoutStepOnePage.hasError());
    }
}
