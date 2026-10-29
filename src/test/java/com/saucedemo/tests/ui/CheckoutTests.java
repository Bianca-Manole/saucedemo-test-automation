package com.saucedemo.tests.ui;

import com.saucedemo.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.classes.CheckoutInfo;
import testdata.classes.Product;
import testdata.pages.Checkout;

public class CheckoutTests extends BaseTest {

    @BeforeEach
    public void openCheckout() {
        loginAsStandardUser();
        inventoryPage.addToCart(new Product("backpack").getName());
        header.openCart();
        cartPage.checkout();
    }

    @Test
    public void checkoutInformationPageShowsFormAndButtons() {
        Assertions.assertTrue(checkoutStepOnePage.isOpen(), "Checkout step one should be open");
        Assertions.assertAll("Checkout information page",
                () -> Assertions.assertEquals(Checkout.CHECKOUT_TITLE_ONE_TEXT, checkoutStepOnePage.getTitleText(), "Title should match"),
                () -> Assertions.assertTrue(checkoutStepOnePage.isCheckoutFormVisible(), "Checkout form should be visible"),
                () -> Assertions.assertTrue(checkoutStepOnePage.isContinueButtonVisible(), "Continue button should be visible"),
                () -> Assertions.assertTrue(checkoutStepOnePage.isCancelButtonVisible(), "Cancel button should be visible"));
    }

    @Test
    public void validInformationLeadsToOverview() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("valid"));

        assertOverviewIsOpen();
    }

    @Test
    public void emptyFirstNameShowsError() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("emptyFirstName"));

        Assertions.assertEquals(Checkout.ERROR_FIRST_NAME_TEXT, checkoutStepOnePage.getErrorMessage(),
                "Missing first name should show an error");
    }

    @Test
    public void emptyLastNameShowsError() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("emptyLastName"));

        Assertions.assertEquals(Checkout.ERROR_LAST_NAME_TEXT, checkoutStepOnePage.getErrorMessage(),
                "Missing last name should show an error");
    }

    @Test
    public void emptyZipShowsError() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("emptyZip"));

        Assertions.assertEquals(Checkout.ERROR_POSTAL_CODE_TEXT, checkoutStepOnePage.getErrorMessage(),
                "Missing postal code should show an error");
    }

    @Test
    public void firstNameAtMaxLengthIsAccepted() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("firstNameMaxLength"));

        assertOverviewIsOpen();
    }

    @Test
    public void firstNameBelowMaxLengthIsAccepted() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("firstNameBelowMaxLength"));

        assertOverviewIsOpen();
    }

    @Test
    public void zipAtMaxLengthIsAccepted() {
        checkoutStepOnePage.submitInformation(new CheckoutInfo("zipMaxLength"));

        assertOverviewIsOpen();
    }

    private void assertOverviewIsOpen() {
        Assertions.assertTrue(checkoutStepTwoPage.isOpen(), "Checkout overview should be open");
        Assertions.assertAll("Checkout overview page",
                () -> Assertions.assertEquals(Checkout.CHECKOUT_TITLE_TWO_TEXT, checkoutStepTwoPage.getTitleText(), "Title should match"),
                () -> Assertions.assertTrue(checkoutStepTwoPage.isFinishButtonVisible(), "Finish button should be visible"),
                () -> Assertions.assertTrue(checkoutStepTwoPage.isCancelButtonVisible(), "Cancel button should be visible"));
    }
}
