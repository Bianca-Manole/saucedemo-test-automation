package com.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testdata.classes.CheckoutUser;
import testdata.pages.Cart;
import testdata.pages.Checkout;
import testdata.pages.Inventory;
import testdata.pages.Login;

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
        inventoryPage.addToCart(Inventory.BACKPACK_INVENTORY_ADD_TO_CART_BUTTON);
        inventoryPage.clickCartButton();
        cartPage.clickCheckout();
        Assertions.assertTrue(checkoutStepOnePage.isCheckoutFormDisplayed());
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
        Assertions.assertEquals(Checkout.ERROR_FIRST_NAME_TEXT, checkoutStepTwoPage.getCheckOutInfoErrorText());
    }

    @Test
    public void enterEmptyLastNameCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(emptyLastNameCheckoutInfo);
        Assertions.assertEquals(Checkout.ERROR_LAST_NAME_TEXT, checkoutStepTwoPage.getCheckOutInfoErrorText());
    }

    @Test
    public void enterEmptyZipCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(emptyZipCheckoutInfo);
        Assertions.assertEquals(Checkout.ERROR_POSTAL_CODE_TEXT, checkoutStepTwoPage.getCheckOutInfoErrorText());
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
        Assertions.assertTrue(checkoutStepTwoPage.isErrorDisplayed());
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
        Assertions.assertTrue(checkoutStepTwoPage.isErrorDisplayed());
    }

    @Test
    public void enterZipAlphaCharactersCheckoutInfo() {
        checkoutStepOnePage.enterDataCheckout(zipAlphaCharactersCheckoutInfo);
        Assertions.assertTrue(checkoutStepTwoPage.isErrorDisplayed());
    }

    @Test
    public void checkOrderDetails() {
        checkoutStepOnePage.enterDataCheckout(validCheckoutInfo);
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertEquals(Inventory.BACKPACK_NAME, cartPage.getItemInCartName());
        Assertions.assertEquals(Inventory.BACKPACK_PRICE, cartPage.getItemPriceInCart());
        Assertions.assertEquals(Inventory.BACKPACK_DESCRIPTION, cartPage.getItemDescriptionInCart());

        Assertions.assertEquals(Checkout.PAYMENT_METHOD_TEXT, checkoutStepTwoPage.getPaymentMethodText());
        Assertions.assertEquals(Checkout.SHIPPING_METHOD_TEXT, checkoutStepTwoPage.getShippingMethodText());
        Assertions.assertTrue(checkoutStepTwoPage.isTotalCorrect());
    }

    @Test
    public void checkFinishOrder() {
        checkoutStepOnePage.enterDataCheckout(validCheckoutInfo);
        checkoutStepTwoPage.clickFinishButton();
        Assertions.assertEquals(Checkout.CHECKOUT_COMPLETE_TITLE_TEXT, checkoutStepTwoPage.getCheckoutCompleteText());
        Assertions.assertEquals(Checkout.COMPLETE_MESSAGE_TEXT, checkoutStepTwoPage.getCompleteOrderTitle());
        Assertions.assertTrue(checkoutStepTwoPage.isBackHomeButtonDisplayed());
    }

}
