package com.saucedemo.tests;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// API tests only: mvn test -Dtest=ApiSuite
@Suite
@SuiteDisplayName("API Tests")
@SelectPackages("com.saucedemo.tests.api")
public class ApiSuite {
}
