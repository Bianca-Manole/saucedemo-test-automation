package com.saucedemo.tests;

import com.saucedemo.tests.api.AuthApiTests;
import com.saucedemo.tests.api.BookingApiTests;
import com.saucedemo.tests.ui.CartTests;
import com.saucedemo.tests.ui.CheckoutTests;
import com.saucedemo.tests.ui.E2ETests;
import com.saucedemo.tests.ui.LoginTests;
import com.saucedemo.tests.ui.ProductTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// JUnit equivalent of testng.xml: which test classes run, and in what order.
// API tests first: they are fast and tell quickly whether the backend is reachable.
@Suite
@SuiteDisplayName("Full Regression (API + UI)")
@SelectClasses({
        AuthApiTests.class,
        BookingApiTests.class,
        LoginTests.class,
        ProductTests.class,
        CartTests.class,
        CheckoutTests.class,
        E2ETests.class
})
public class RegressionSuite {
}
