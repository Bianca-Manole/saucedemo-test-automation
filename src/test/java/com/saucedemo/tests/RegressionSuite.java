package com.saucedemo.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// JUnit equivalent of testng.xml: which test classes run, and in what order.
@Suite
@SuiteDisplayName("SauceDemo Regression")
@SelectClasses({
        LoginTests.class,
        ProductTests.class,
        CartTests.class,
        CheckoutTests.class,
        E2ETests.class
})
public class RegressionSuite {
}
