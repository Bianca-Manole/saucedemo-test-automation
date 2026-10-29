package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.classes.Product;
import testdata.pages.Inventory;

import java.util.Comparator;
import java.util.List;

public class ProductTests extends BaseTest {

    private final Product backpack = new Product("backpack");

    @BeforeEach
    public void login() {
        loginAsStandardUser();
    }

    @Test
    public void sortByNameAToZ() {
        inventoryPage.sortBy(Inventory.FILTER_AZ);

        List<String> names = inventoryPage.getProductNames();
        Assertions.assertEquals(names.stream().sorted().toList(), names,
                "Products should be sorted by name A to Z");
    }

    @Test
    public void sortByNameZToA() {
        inventoryPage.sortBy(Inventory.FILTER_ZA);

        List<String> names = inventoryPage.getProductNames();
        Assertions.assertEquals(names.stream().sorted(Comparator.reverseOrder()).toList(), names,
                "Products should be sorted by name Z to A");
    }

    @Test
    public void sortByPriceLowToHigh() {
        inventoryPage.sortBy(Inventory.FILTER_LOHI);

        List<Double> prices = inventoryPage.getProductPrices();
        Assertions.assertEquals(prices.stream().sorted().toList(), prices,
                "Products should be sorted by price low to high");
    }

    @Test
    public void sortByPriceHighToLow() {
        inventoryPage.sortBy(Inventory.FILTER_HILO);

        List<Double> prices = inventoryPage.getProductPrices();
        Assertions.assertEquals(prices.stream().sorted(Comparator.reverseOrder()).toList(), prices,
                "Products should be sorted by price high to low");
    }

    @Test
    public void clickingProductNameOpensItsDetailPage() {
        inventoryPage.openProduct(backpack.getName());

        Assertions.assertTrue(itemPage.isOpen(), "Product detail page should be open");
        Assertions.assertEquals(backpack.getName(), itemPage.getItemName(), "Product name should match");
        Assertions.assertEquals(backpack.getDescription(), itemPage.getItemDescription(), "Product description should match");
        Assertions.assertEquals(backpack.getPrice(), itemPage.getItemPrice(), "Product price should match");
    }

    @Test
    public void addingProductFromDetailPageShowsRemoveButtonAndCartBadge() {
        inventoryPage.openProduct(backpack.getName());
        itemPage.addToCart();

        Assertions.assertTrue(itemPage.isRemoveButtonVisible(), "Remove button should replace Add to cart");
        Assertions.assertEquals("1", header.getCartBadgeCount(), "Cart badge should show 1 item");
    }
}
