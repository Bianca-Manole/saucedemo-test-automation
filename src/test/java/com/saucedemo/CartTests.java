package com.saucedemo;

import com.saucedemo.components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.URL;
import testdata.classes.Product;
import testdata.pages.Cart;

public class CartTests extends BaseTest {

    Product backpack = new Product("backpack");

    @BeforeEach
    @Override
    public void beforeTests() {
        super.beforeTests();
        loginPage.authentication(standardUser);
        Assertions.assertEquals(Header.LOGO_TEXT, header.getLogoText());
        Assertions.assertTrue(inventoryPage.isItemListDisplayed());
        Assertions.assertTrue(header.isLogoutButtonDisplayed());
    }

    @Test
    public void addAndRemoveFromCart() {
        inventoryPage.addToCart(InventoryPage.BACKPACK_ADD_TO_CART_BUTTON);
        Assertions.assertTrue(inventoryPage.isButtonDisplayed(InventoryPage.BACKPACK_REMOVE_BUTTON));
        Assertions.assertTrue(header.isCartBadgeDisplayed());

        header.clickCartButton();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CART, driver.getCurrentUrl());
        Assertions.assertEquals(Cart.TITLE_TEXT, cartPage.getTitleText());
        Assertions.assertTrue(cartPage.isCheckOutButtonDisplayed());
        Assertions.assertTrue(cartPage.isContinueShoppingButtonDisplayed());
        Assertions.assertEquals(backpack.getName(), cartPage.getItemInCartName());
        Assertions.assertEquals(backpack.getDescription(), cartPage.getItemDescriptionInCart());
        Assertions.assertEquals("1", cartPage.getItemQuantityInCart());

        cartPage.clickRemove();
        Assertions.assertTrue(cartPage.isCartEmpty());

        cartPage.clickContinueShopping();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_INVENTORY, driver.getCurrentUrl());
        Assertions.assertTrue(inventoryPage.isButtonDisplayed(InventoryPage.BACKPACK_ADD_TO_CART_BUTTON));
        Assertions.assertFalse(inventoryPage.isButtonDisplayed(InventoryPage.BACKPACK_REMOVE_BUTTON));
    }
}
