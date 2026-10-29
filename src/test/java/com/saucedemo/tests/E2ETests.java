package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testdata.classes.CheckoutInfo;
import testdata.classes.Product;
import testdata.pages.Checkout;

public class E2ETests extends BaseTest {

    @Test
    public void standardUserCanCompletePurchase() {
        Product backpack = new Product("backpack");

        loginAsStandardUser();
        Assertions.assertTrue(inventoryPage.isOpen(), "Inventory page should be open after login");

        inventoryPage.openProduct(backpack.getName());
        Assertions.assertTrue(itemPage.isOpen(), "Product detail page should be open");
        Assertions.assertEquals(backpack.getName(), itemPage.getItemName(), "Product name should match");
        Assertions.assertEquals(backpack.getPrice(), itemPage.getItemPrice(), "Product price should match");
        Assertions.assertEquals(backpack.getDescription(), itemPage.getItemDescription(), "Product description should match");

        itemPage.addToCart();
        header.openCart();
        Assertions.assertTrue(cartPage.isOpen(), "Cart page should be open");
        Assertions.assertEquals(backpack.getName(), cartPage.getItemName(), "Cart product name should match");
        Assertions.assertEquals(backpack.getPrice(), cartPage.getItemPrice(), "Cart product price should match");
        Assertions.assertEquals("1", cartPage.getItemQuantity(), "Cart quantity should be 1");

        cartPage.checkout();
        Assertions.assertTrue(checkoutStepOnePage.isOpen(), "Checkout step one should be open");
        checkoutStepOnePage.submitInformation(new CheckoutInfo("valid"));

        Assertions.assertTrue(checkoutStepTwoPage.isOpen(), "Checkout overview should be open");
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText(), "Overview title should match");
        Assertions.assertEquals(backpack.getName(), checkoutStepTwoPage.getItemName(), "Overview product name should match");
        Assertions.assertEquals(backpack.getPrice(), checkoutStepTwoPage.getItemPrice(), "Overview product price should match");
        Assertions.assertEquals(backpack.getDescription(), checkoutStepTwoPage.getItemDescription(), "Overview product description should match");
        Assertions.assertTrue(checkoutStepTwoPage.isPaymentInformationVisible(), "Payment information should be visible");
        Assertions.assertEquals(Checkout.PAYMENT_METHOD_TEXT, checkoutStepTwoPage.getPaymentMethod(), "Payment method should match");
        Assertions.assertEquals(Checkout.SHIPPING_METHOD_TEXT, checkoutStepTwoPage.getShippingMethod(), "Shipping method should match");

        double expectedTotal = Math.round((checkoutStepTwoPage.getItemTotal() + checkoutStepTwoPage.getTax()) * 100.0) / 100.0;
        Assertions.assertEquals(expectedTotal, checkoutStepTwoPage.getTotal(), 0.001, "Total should equal item total + tax");

        checkoutStepTwoPage.finish();
        Assertions.assertTrue(checkoutCompletePage.isOpen(), "Checkout complete page should be open");
        Assertions.assertEquals(Checkout.CHECKOUT_COMPLETE_TITLE_TEXT, checkoutCompletePage.getTitleText(), "Complete title should match");
        Assertions.assertEquals(Checkout.COMPLETE_MESSAGE_TEXT, checkoutCompletePage.getCompleteHeader(), "Thank you message should match");
        Assertions.assertTrue(checkoutCompletePage.isBackHomeButtonVisible(), "Back home button should be visible");
    }
}
