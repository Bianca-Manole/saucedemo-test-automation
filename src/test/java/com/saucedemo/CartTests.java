package com.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testdata.pages.Cart;
import testdata.pages.Checkout;
import testdata.pages.Inventory;
import testdata.pages.Login;

public class CartTests extends BaseTest{

    @BeforeEach
    @Override
    public void beforeTests() {
        super.beforeTests();
        loginPage.authentication(standardUser);
        Assertions.assertEquals(Login.LOGO_TEXT, inventoryPage.getLogoText());
        Assertions.assertTrue(inventoryPage.isItemListDisplayed());
        Assertions.assertTrue(inventoryPage.isLogoutButtonDisplayed());
    }

    @Test
    public void checkItemIsInCart() {

        inventoryPage.addToCart(Inventory.BACKPACK_INVENTORY_ADD_TO_CART_BUTTON);
        inventoryPage.clickCartButton();
        Assertions.assertEquals(Cart.TITLE_TEXT, cartPage.getTitleText());
        Assertions.assertTrue(cartPage.isCheckOutButtonDisplayed());
        Assertions.assertTrue(cartPage.isContinueShoppingButtonDisplayed());

        Assertions.assertEquals(Inventory.BACKPACK_NAME, cartPage.getItemInCartName());
        Assertions.assertEquals(Inventory.BACKPACK_PRICE, cartPage.getItemPriceInCart());
        Assertions.assertEquals(Inventory.BACKPACK_DESCRIPTION, cartPage.getItemDescriptionInCart());
    }

    @Test
    public void checkCheckoutButton() {
        inventoryPage.addToCart(Inventory.BACKPACK_INVENTORY_ADD_TO_CART_BUTTON);
        inventoryPage.clickCartButton();
        cartPage.clickCheckout();
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_ONE_TEXT, checkoutStepOnePage.getTitleText());
        Assertions.assertTrue(checkoutStepOnePage.isCheckoutFormDisplayed());
        Assertions.assertTrue(checkoutStepOnePage.isContinueButtonDisplayed());
        Assertions.assertTrue(checkoutStepOnePage.isCancelButtonDisplayed());
    }
}
