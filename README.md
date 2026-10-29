# SauceDemo Test Automation Framework

[![Tests](https://github.com/Bianca-Manole/final-project/actions/workflows/tests.yml/badge.svg)](https://github.com/Bianca-Manole/final-project/actions/workflows/tests.yml)

UI and API test automation framework for [saucedemo.com](https://www.saucedemo.com/) (Swag Labs demo shop), built with **Java 17, Selenium 4, RestAssured and JUnit 5**, using the Page Object Model.

**41 automated tests** — 24 UI, 17 API — run from one command, locally or in GitHub Actions.

## Tech stack

| Purpose | Tool |
|---|---|
| Language / build | Java 17, Maven |
| UI automation | Selenium WebDriver 4 (Selenium Manager resolves drivers) |
| API automation | RestAssured 5, JSON Schema Validator |
| Test runner | JUnit 5 (Jupiter, Platform Suite, parameterized tests) |
| JSON | Jackson |
| Reporting | Maven Surefire Report, screenshots on failure |
| CI | GitHub Actions (headless Chrome) |

## Project structure

```
src/main/java/com/saucedemo/
  base/BasePage              shared actions with explicit waits (click, type, textOf, isVisible...)
  pages/                     one Page Object per screen + components/Header
  api/                       ApiClient, clients/SauceDemoClient, models/WebManifest
  utils/                     ConfigReader, Reader (JSON test data), ScreenshotUtil
  testdata/                  test data classes (User, Product, CheckoutInfo) and expected UI texts
src/main/resources/          testdata/*.json, schemas/manifest-schema.json
src/test/java/com/saucedemo/
  base/                      BaseTest (browser lifecycle), ScreenshotOnFailureExtension
  tests/ui/                  Login, Product, Cart, Checkout, E2E tests
  tests/api/                 GET and POST tests
  tests/                     RegressionSuite, UiSuite, ApiSuite
config.properties            environment settings
```

## Running the tests

Requirements: JDK 17+, Maven, Chrome (or Firefox / Edge).

```bash
mvn clean test                          # full regression: API first, then UI
mvn test -Dtest=ApiSuite                # API tests only (~6 s)
mvn test -Dtest=UiSuite                 # UI tests only
mvn test -Dtest=LoginTests              # one class
mvn clean test -Dheadless=true          # without opening a browser window
mvn clean test -Dbrowser=firefox        # another browser
mvn surefire-report:report-only         # HTML report -> target/reports/surefire.html
```

Every value in `config.properties` (`baseUrl`, `browser`, `headless`, `timeout`, `username`, `password`) can be overridden with `-D<key>=<value>` without editing the file.

## Design principles

- **Page Object Model** — locators are private to page classes; pages expose user-intent methods (`login`, `addToCart`, `checkout`) and report facts, never assert.
- **Tests only assert** — no locators, waits, URLs or credentials in test classes. Expected values come from test data, calculations (sorting, order total) are verified in the test.
- **Independent tests** — a fresh browser per test, no shared state, no assertions in setup; any test passes when run on its own or in any order.
- **Explicit waits only** — every interaction goes through `BasePage`, which waits for the right condition and retries on re-rendered elements. No `Thread.sleep` anywhere.
- **Externalised configuration and data** — environment in `config.properties`, test data in JSON, expected texts in constants.
- **Readable failures** — every assertion has a message, related checks are grouped with `assertAll` so one failure does not hide others, failed UI tests save a screenshot.

## Test coverage

| Area | Class | Tests | What is covered |
|---|---|---:|---|
| API (GET) | `SauceDemoGetApiTests` | 11 | home page served as HTML, response time, `manifest.json` content + JSON schema, every declared icon, JS bundle, `robots.txt`, inner routes return 404 on the server |
| API (POST) | `SauceDemoPostApiTests` | 6 | login data, cart data and any POST are refused (405) and no session is created |
| Login | `LoginTests` | 5 | valid login, locked-out user, invalid credentials, menu, logout |
| Products | `ProductTests` | 6 | sorting by name and price (both directions; A→Z starts from Z→A because A→Z is the default order), product detail page, add to cart from detail page |
| Cart | `CartTests` | 5 | badge and button state, cart contents, remove, continue shopping |
| Checkout | `CheckoutTests` | 7 | form and buttons, valid data, each required field, long first name and postal code accepted (the app has no length limit) |
| End-to-end | `E2ETests` | 1 | full purchase: login → product → cart → checkout → overview (total = subtotal + tax) → confirmation |

### About the API tests

saucedemo is a static React single-page application hosted on GitHub Pages — it has **no backend API**. Login is validated in the browser and the cart is kept in `localStorage`. The API tests therefore verify what the server really does over HTTP:

- `GET` serves the app shell, `manifest.json`, icons, the JS bundle and `robots.txt`; inner routes such as `/inventory.html` return **404** because they exist only client-side.
- `POST` (credentials, cart data, any path) returns **405 Not Allowed** and sets no session cookie — proof that login and cart cannot be tested below the UI on this application, which is why they are covered by the UI suite.

## Reports

- `mvn surefire-report:report-only` generates `target/reports/surefire.html`.
- In CI the report is attached to every run as the `test-report` artifact (Actions tab → run → Artifacts), plus `failure-screenshots` when a UI test fails.
- [`reports/`](reports/) holds a snapshot of the latest full local run — see [`reports/README.md`](reports/README.md).
