# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the whole suite (RegressionSuite decides which classes run and in what order)
mvn clean test

# Only API tests / only UI tests
mvn test -Dtest=ApiSuite
mvn test -Dtest=UiSuite

# Run a single test class / method
mvn test -Dtest=LoginTests
mvn test -Dtest=LoginTests#logoutReturnsToLoginPage

# Any config.properties key can be overridden per run
mvn clean test -Dheadless=true -Dbrowser=firefox

# HTML report after a run -> target/reports/surefire.html
mvn surefire-report:report-only
```

Tests need a local Chrome (or Firefox/Edge, see `browser` in `config.properties`). Selenium Manager resolves the driver; there is no WebDriverManager.

CI: `.github/workflows/tests.yml` runs `mvn clean test -Dheadless=true` on every push / PR (Ubuntu, Java 17) and uploads the HTML report (`test-report`) and failure screenshots as artifacts. `README.md` is the human-facing project description (badge, structure, coverage); keep it in sync when tests or structure change.

## Architecture

**Selenium 4 + RestAssured + JUnit 5** test framework for [saucedemo.com](https://www.saucedemo.com/): UI tests plus HTTP-level API tests (GET/POST) on saucedemo itself. Only saucedemo is tested — do not add tests for other sites. Layered as base / pages + api / utils / tests.

saucedemo is a static React single-page app hosted on GitHub Pages: there is no backend API. Login is checked in the browser (cookie `session-username`), the cart lives in `localStorage` (`cart-contents`). Over HTTP the server only serves files: `GET /`, `/manifest.json`, `/robots.txt`, icons and `/assets/*.js` return 200; inner pages like `/inventory.html` return 404 when requested directly; every `POST`/`PUT`/`DELETE` returns `405 Not Allowed`. `SauceDemoGetApiTests` / `SauceDemoPostApiTests` assert exactly this.

| Layer | Location | Job |
|---|---|---|
| `base/BasePage` | `src/main/java/com/saucedemo/base` | Shared actions with explicit waits: `click`, `type`, `textOf`, `textsOf`, `isVisible`, `isGone`, `isAtUrl`. Retries on stale elements. |
| `pages/` | `src/main/java/com/saucedemo/pages` | One class per page (+ `components/Header`), extends `BasePage`. Private `By` locators, intent-named methods, own URL path for `isOpen()`. |
| `utils/` | `src/main/java/com/saucedemo/utils` | `ConfigReader` (system property first, then `config.properties`), `Reader` (JSON test data from classpath via Jackson), `ScreenshotUtil`. |
| `base/BaseTest` | `src/test/java/com/saucedemo/base` | Fresh browser per test (`@BeforeEach`, headless or maximized), always quit in `@AfterEach`, creates page objects, `loginAsStandardUser()`. |
| `api/` | `src/main/java/com/saucedemo/api` | `ApiClient` (base request on `baseUrl`), `clients/SauceDemoClient` (GET/POST methods returning the raw `Response`), `models/WebManifest` (record mapped from `/manifest.json` by Jackson). |
| `tests/ui`, `tests/api` | `src/test/java/com/saucedemo/tests` | One class per feature, Arrange / Act / Assert. `RegressionSuite` (`@Suite`, API first, then UI) is the testng.xml equivalent; `ApiSuite` / `UiSuite` run one layer. |

### Rules

- **API: no URL, header or request building in a test; no assertion in a client.** Clients return `Response`; tests assert status code and body.
- **No locator, `driver.findElement` or wait in a test class; no assertion in a page class.** Pages report facts (texts, numbers, booleans); tests judge them, e.g. sorting and total calculations are asserted in the test.
- **No URL, credential or timeout hardcoded in tests** — they come from `config.properties` (project root).
- **No `Thread.sleep`** — use the `BasePage` helpers.
- Every assertion has a message; test names describe the expected behaviour.
- Checks on the same screen go in one `assertAll`; a "page is open" check stays a plain assertion before it, so the test stops early on the wrong page.
- No assertions in `@BeforeEach`; each test must pass when run on its own.

### Test data

- `config.properties`: `baseUrl`, `browser`, `headless`, `timeout`, `username`/`password` of the standard user (`User.fromConfig()`).
- `src/main/resources/testdata/*.json`, loaded by key through model classes in `testdata.classes`: `User("locked")`, `CheckoutInfo("valid")`, `Product("backpack")`. Missing files/keys/fields fail fast (`JsonNode.required`).
- Expected UI texts live in `testdata.pages.*` (`Login`, `Inventory`, `Cart`, `Checkout`).
- API: same `baseUrl` and credentials as the UI; JSON schema for the manifest in `src/main/resources/schemas/manifest-schema.json`.

### Failures

`ScreenshotOnFailureExtension` (registered on `BaseTest`) saves a PNG to `screenshots/` when a test or its `@BeforeEach` fails, before the browser is closed.

Surefire only includes `RegressionSuite`; adding a new test class means adding it to `@SelectClasses` there (and to `UiSuite` for UI classes; `ApiSuite` picks up the `tests.api` package automatically).

`reports/` holds a committed copy of the last full run: `surefire.html` (+ assets) and `README.md` (the same results as Markdown, readable on GitHub). It is refreshed by hand after a run.
