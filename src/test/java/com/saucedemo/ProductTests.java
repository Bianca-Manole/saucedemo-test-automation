package com.saucedemo;

import com.saucedemo.components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.classes.Product;
import testdata.pages.Inventory;

public class ProductTests extends BaseTest {

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
        inventoryPage.clickItemByName(backpack.getName());
        Assertions.assertEquals(backpack.getName(), itemPage.getItemName());
        Assertions.assertEquals(backpack.getDescription(), itemPage.getItemDescription());
        Assertions.assertEquals(backpack.getPrice(), itemPage.getItemPrice());
    }

    @Test
    public void addToCartFromItemPage() {
        inventoryPage.clickItemByName(backpack.getName());
        itemPage.clickAddToCart();
        Assertions.assertTrue(itemPage.isRemoveButtonDisplayed());
        Assertions.assertTrue(header.isCartBadgeDisplayed());
    }
}
