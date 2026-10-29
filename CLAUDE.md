# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the whole suite (RegressionSuite decides which classes run and in what order)
mvn clean test

# Run a single test class / method
mvn test -Dtest=LoginTests
mvn test -Dtest=LoginTests#logoutReturnsToLoginPage

# HTML report after a run -> target/reports/surefire.html
mvn surefire-report:report-only
```

Tests need a local Chrome (or Firefox/Edge, see `browser` in `config.properties`). Selenium Manager resolves the driver; there is no WebDriverManager.

## Architecture

**Selenium 4 + JUnit 5** UI test framework for [saucedemo.com](https://www.saucedemo.com/), layered as base / pages / utils / tests.

| Layer | Location | Job |
|---|---|---|
| `base/BasePage` | `src/main/java/com/saucedemo/base` | Shared actions with explicit waits: `click`, `type`, `textOf`, `textsOf`, `isVisible`, `isGone`, `isAtUrl`. Retries on stale elements. |
| `pages/` | `src/main/java/com/saucedemo/pages` | One class per page (+ `components/Header`), extends `BasePage`. Private `By` locators, intent-named methods, own URL path for `isOpen()`. |
| `utils/` | `src/main/java/com/saucedemo/utils` | `ConfigReader` (reads `config.properties`), `Reader` (JSON test data from classpath), `ScreenshotUtil`. |
| `base/BaseTest` | `src/test/java/com/saucedemo/base` | Fresh browser per test (`@BeforeEach`), always quit in `@AfterEach`, creates page objects, `loginAsStandardUser()`. |
| `tests/` | `src/test/java/com/saucedemo/tests` | One class per feature, Arrange / Act / Assert. `RegressionSuite` (`@Suite`) is the testng.xml equivalent. |

### Rules

- **No locator, `driver.findElement` or wait in a test class; no assertion in a page class.** Pages report facts (texts, numbers, booleans); tests judge them, e.g. sorting and total calculations are asserted in the test.
- **No URL, credential or timeout hardcoded in tests** — they come from `config.properties` (project root).
- **No `Thread.sleep`** — use the `BasePage` helpers.
- Every assertion has a message; test names describe the expected behaviour.
- No assertions in `@BeforeEach`; each test must pass when run on its own.

### Test data

- `config.properties`: `baseUrl`, `browser`, `timeout`, `username`/`password` of the standard user (`User.fromConfig()`).
- `src/main/resources/testdata/*.json`, loaded by key through model classes in `testdata.classes`: `User("locked")`, `CheckoutInfo("valid")`, `Product("backpack")`. Missing files/keys fail fast.
- Expected UI texts live in `testdata.pages.*` (`Login`, `Inventory`, `Cart`, `Checkout`).

### Failures

`ScreenshotOnFailureExtension` (registered on `BaseTest`) saves a PNG to `screenshots/` when a test or its `@BeforeEach` fails, before the browser is closed.

Surefire only includes `RegressionSuite`; adding a new test class means adding it to `@SelectClasses` there.
