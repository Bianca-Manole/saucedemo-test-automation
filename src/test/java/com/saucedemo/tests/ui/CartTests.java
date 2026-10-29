package com.saucedemo.tests.ui;

import com.saucedemo.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.classes.Product;
import testdata.pages.Cart;

public class CartTests extends BaseTest {

    private final Product backpack = new Product("backpack");

    @BeforeEach
    public void loginAndAddProduct() {
        loginAsStandardUser();
        inventoryPage.addToCart(backpack.getName());
    }

    @Test
    public void addingProductShowsRemoveButtonAndCartBadge() {
        Assertions.assertTrue(inventoryPage.isRemoveButtonVisible(backpack.getName()),
                "Remove button should replace Add to cart");
        Assertions.assertEquals("1", header.getCartBadgeCount(), "Cart badge should show 1 item");
    }

    @Test
    public void cartShowsAddedProductDetails() {
        header.openCart();

        Assertions.assertTrue(cartPage.isOpen(), "Cart page should be open");
        Assertions.assertEquals(Cart.TITLE_TEXT, cartPage.getTitleText(), "Cart title should match");
        Assertions.assertEquals(backpack.getName(), cartPage.getItemName(), "Product name should match");
        Assertions.assertEquals(backpack.getDescription(), cartPage.getItemDescription(), "Product description should match");
        Assertions.assertEquals(backpack.getPrice(), cartPage.getItemPrice(), "Product price should match");
        Assertions.assertEquals("1", cartPage.getItemQuantity(), "Product quantity should be 1");
    }

    @Test
    public void cartShowsCheckoutAndContinueShoppingButtons() {
        header.openCart();

        Assertions.assertTrue(cartPage.isCheckoutButtonVisible(), "Checkout button should be visible");
        Assertions.assertTrue(cartPage.isContinueShoppingButtonVisible(), "Continue shopping button should be visible");
    }

    @Test
    public void removingProductEmptiesCart() {
        header.openCart();
        cartPage.removeItem();

        Assertions.assertTrue(cartPage.isEmpty(), "Cart should be empty after removing the product");
    }

    @Test
    public void continueShoppingAfterRemovingShowsAddToCartAgain() {
        header.openCart();
        cartPage.removeItem();
        cartPage.continueShopping();

        Assertions.assertTrue(inventoryPage.isOpen(), "Continue shopping should return to the inventory page");
        Assertions.assertTrue(inventoryPage.isAddToCartButtonVisible(backpack.getName()),
                "Add to cart button should be back after removing the product");
    }
}
