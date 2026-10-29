package com.saucedemo.tests;

import com.saucedemo.tests.ui.CartTests;
import com.saucedemo.tests.ui.CheckoutTests;
import com.saucedemo.tests.ui.E2ETests;
import com.saucedemo.tests.ui.LoginTests;
import com.saucedemo.tests.ui.ProductTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// UI tests only: mvn test -Dtest=UiSuite
@Suite
@SuiteDisplayName("UI Tests")
@SelectClasses({
        LoginTests.class,
        ProductTests.class,
        CartTests.class,
        CheckoutTests.class,
        E2ETests.class
})
public class UiSuite {
}
