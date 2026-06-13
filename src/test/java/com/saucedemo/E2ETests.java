package com.saucedemo;

import com.saucedemo.components.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testdata.URL;
import testdata.classes.CheckoutUser;
import testdata.classes.Product;
import testdata.pages.Checkout;

public class E2ETests extends BaseTest {

    @Test
    public void completeBookingFlow() {
        Product backpack = new Product("backpack");

        loginPage.authentication(standardUser);
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_INVENTORY, driver.getCurrentUrl());
        Assertions.assertEquals(Header.LOGO_TEXT, header.getLogoText());

        inventoryPage.clickItemByName(backpack.getName());
        Assertions.assertTrue(driver.getCurrentUrl().startsWith(URL.MAIN + URL.PATH_TO_ITEM_DETAILS));
        Assertions.assertEquals(backpack.getName(), itemPage.getItemName());
        Assertions.assertEquals(backpack.getPrice(), itemPage.getItemPrice());
        Assertions.assertEquals(backpack.getDescription(), itemPage.getItemDescription());

        itemPage.clickAddToCart();
        header.clickCartButton();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CART, driver.getCurrentUrl());
        Assertions.assertEquals(backpack.getName(), cartPage.getItemInCartName());
        Assertions.assertEquals(backpack.getPrice(), cartPage.getItemPriceInCart());
        Assertions.assertEquals("1", cartPage.getItemQuantityInCart());

        cartPage.clickCheckout();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CHECKOUT_STEP_ONE, driver.getCurrentUrl());
        checkoutStepOnePage.enterDataCheckout(new CheckoutUser("valid"));

        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CHECKOUT_STEP_TWO, driver.getCurrentUrl());
        Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText());
        Assertions.assertEquals(backpack.getName(), checkoutStepTwoPage.getCheckoutItemName());
        Assertions.assertEquals(backpack.getPrice(), checkoutStepTwoPage.getCheckoutItemPrice());
        Assertions.assertEquals(backpack.getDescription(), checkoutStepTwoPage.getCheckoutItemDescription());
        Assertions.assertTrue(checkoutStepTwoPage.isPaymentInformationDisplayed());
        Assertions.assertEquals(Checkout.PAYMENT_METHOD_TEXT, checkoutStepTwoPage.getPaymentMethodText());
        Assertions.assertEquals(Checkout.SHIPPING_METHOD_TEXT, checkoutStepTwoPage.getShippingMethodText());
        Assertions.assertTrue(checkoutStepTwoPage.isTotalCorrect());

        checkoutStepTwoPage.clickFinishButton();
        Assertions.assertEquals(URL.MAIN + URL.PATH_TO_CHECKOUT_COMPLETE, driver.getCurrentUrl());

        Assertions.assertEquals(Checkout.CHECKOUT_COMPLETE_TITLE_TEXT, checkoutStepTwoPage.getCheckoutCompleteText());
        Assertions.assertEquals(Checkout.COMPLETE_MESSAGE_TEXT, checkoutStepTwoPage.getCompleteOrderTitle());
        Assertions.assertTrue(checkoutStepTwoPage.isBackHomeButtonDisplayed());
    }
}
