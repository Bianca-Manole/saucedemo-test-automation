package com.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testdata.pages.Inventory;
import testdata.pages.Item;
import testdata.pages.Login;

public class ProductTests extends BaseTest {
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
    public void addAndRemoveProductFromCart() {
        inventoryPage.addToCart(Inventory.ONESIE_INVENTORY_ADD_TO_CART_BUTTON);
        Assertions.assertTrue(driver.findElement(Inventory.ONESIE_REMOVE_BUTTON).isDisplayed());
        Assertions.assertTrue(driver.findElement(Inventory.CART_BUTTON_WITH_ITEMS).isDisplayed());
        inventoryPage.removeFromCart(Inventory.ONESIE_REMOVE_BUTTON);
        Assertions.assertTrue(driver.findElement(Inventory.ONESIE_INVENTORY_ADD_TO_CART_BUTTON).isDisplayed());
        Assertions.assertTrue(driver.findElement(Inventory.CART_BUTTON_EMPTY).isDisplayed());
    }

    @Test
    public void filter() {
        inventoryPage.filterBy(Inventory.FILTER_ZA);
        Assertions.assertTrue(inventoryPage.isNameSortedZA());

        inventoryPage.filterBy(Inventory.FILTER_AZ);
        Assertions.assertTrue(inventoryPage.isNameSortedAZ());

        inventoryPage.filterBy(Inventory.FILTER_HILO);
        Assertions.assertTrue(inventoryPage.isPriceSortedHighLow());

        inventoryPage.filterBy(Inventory.FILTER_LOHI);
        Assertions.assertTrue(inventoryPage.isPriceSortedLowHigh());
    }

    @Test
    public void openItem() {
        inventoryPage.clickItemByName(Inventory.BACKPACK_NAME);
        Assertions.assertEquals(Inventory.BACKPACK_NAME, itemPage.getItemName());
        Assertions.assertEquals(Inventory.BACKPACK_DESCRIPTION, itemPage.getItemDescription());
        Assertions.assertEquals(Inventory.BACKPACK_PRICE, itemPage.getItemPrice());
    }

    @Test
    public void addToCartFromItemPage() {
        inventoryPage.clickItemByName(Inventory.BACKPACK_NAME);
        itemPage.clickAddToCart();
        Assertions.assertTrue(driver.findElement(Item.REMOVE_FROM_CART).isDisplayed());
        Assertions.assertTrue(driver.findElement(Inventory.CART_BUTTON_WITH_ITEMS).isDisplayed());
    }


}
