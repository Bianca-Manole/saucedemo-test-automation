# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Compile the project
mvn compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=LoginTests

# Run a single test method
mvn test -Dtest=LoginTests#loginStandardUser
```

Tests require Chrome to be installed — `BaseTest` launches a real ChromeDriver instance (no headless mode configured).

## Architecture

This is a **Selenium + JUnit 5** end-to-end test suite for [saucedemo.com](https://www.saucedemo.com/).

### Two-layer page object model

**Layer 1 — `testdata.pages.*`** (e.g., `testdata/pages/Login.java`): Pure data classes holding Selenium `By` locators and expected string constants. No driver interaction. These are the single source of truth for selectors and expected text.

**Layer 2 — `com.saucedemo.*`** (e.g., `com/saucedemo/LoginPage.java`): Page action classes that accept a `WebDriver` and use the locators from Layer 1 to interact with the browser. Each page class corresponds to one page of the app.

### Test data via JSON + model classes

User credentials and checkout data live in `src/main/java/testdata/files/*.json`. The `Reader` utility (`com/saucedemo/utils/Reader.java`) loads them by filename. Model classes (`testdata.classes.User`, `testdata.classes.CheckoutUser`) take a string key (e.g., `"standard"`, `"valid"`) and hydrate from the JSON — this is how tests instantiate test users without hardcoding credentials.

### Test structure

All test classes extend `BaseTest`, which:
- Creates a single `ChromeDriver` instance per test class (`@BeforeAll`)
- Navigates to the login page and instantiates all page objects before each test (`@BeforeEach`)
- Clears cookies and storage after each test (`@AfterEach`) to reset state without a full browser restart

Test classes: `LoginTests`, `ProductTests`, `CartTests`, `CheckoutTests`, `E2ETests`.

### URL constants

`testdata.URL` holds all page paths. `Helper.getItemURL(productID)` builds item detail URLs. Item IDs 0–5 are mapped to product constants in `testdata.URL`.
