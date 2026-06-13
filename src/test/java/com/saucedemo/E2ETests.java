package com.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testdata.classes.CheckoutUser;
import testdata.pages.Checkout;
import testdata.pages.Inventory;
import testdata.pages.Login;

public class E2ETests extends BaseTest{
    @Test
    public void completeBookingFlow() {

        loginPage.authentication(standardUser);
        Assertions.assertEquals(Login.LOGO_TEXT, inventoryPage.getLogoText());


        inventoryPage.clickItemByName(Inventory.BACKPACK_NAME);
        Assertions.assertEquals(Inventory.BACKPACK_NAME, itemPage.getItemName());
        Assertions.assertEquals(Inventory.BACKPACK_PRICE, itemPage.getItemPrice());
        Assertions.assertEquals(Inventory.BACKPACK_DESCRIPTION, itemPage.getItemDescription());


        itemPage.clickAddToCart();
        itemPage.clickCartButton();
        Assertions.assertEquals(Inventory.BACKPACK_NAME, cartPage.getItemInCartName());
        Assertions.assertEquals(Inventory.BACKPACK_PRICE, cartPage.getItemPriceInCart());


        cartPage.clickCheckout();
        checkoutStepOnePage.enterDataCheckout(new CheckoutUser("valid"));


        Assertions.assertEquals(Inventory.BACKPACK_NAME, cartPage.getItemInCartName());
        Assertions.assertTrue(checkoutStepTwoPage.isTotalCorrect());


        checkoutStepTwoPage.clickFinishButton();
        Assertions.assertEquals(Checkout.COMPLETE_MESSAGE_TEXT, checkoutStepTwoPage.getCompleteOrderTitle());
    }
}
