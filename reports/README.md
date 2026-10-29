# Test Report — Full Regression (API + UI)

**Result: ✅ ALL TESTS PASSED**

| Total | Passed | Failed | Skipped | Success rate | Duration |
|---:|---:|---:|---:|---:|---:|
| 41 | 41 | 0 | 0 | 100% | 131.5 s |

## Environment

| | |
|---|---|
| Run date | 2026-10-29 |
| Command | `mvn clean test -Dheadless=true` (suite: `RegressionSuite`) |
| UI | https://www.saucedemo.com/ |
| API | https://www.saucedemo.com/ (GET/POST over HTTP) |
| Browser | Chrome 156 (headless) |
| Java | 25.0.2 |
| OS | Windows 11 |
| Frameworks | Selenium 4.30.0, RestAssured 5.5.6, JUnit 5.14.0, Jackson 2.18.2 |

## Summary per test class

| Layer | Test class | Tests | Passed | Failed | Duration |
|---|---|---:|---:|---:|---:|
| API | SauceDemoGetApiTests | 11 | 11 | 0 | 6.1 s |
| API | SauceDemoPostApiTests | 6 | 6 | 0 | 1.0 s |
| UI | LoginTests | 5 | 5 | 0 | 25.2 s |
| UI | ProductTests | 6 | 6 | 0 | 30.3 s |
| UI | CartTests | 5 | 5 | 0 | 24.9 s |
| UI | CheckoutTests | 7 | 7 | 0 | 38.2 s |
| UI | E2ETests | 1 | 1 | 0 | 5.8 s |

## Test cases

### SauceDemoGetApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `manifestDescribesTheSwagLabsApp` | ✅ Passed | 2.6 s |
| `robotsTxtAllowsAllCrawlers` | ✅ Passed | 0.3 s |
| `manifestMatchesJsonSchema` | ✅ Passed | 0.6 s |
| `homePageIsServedAsHtml` | ✅ Passed | 0.2 s |
| `javascriptBundleReferencedByHomePageIsServed` | ✅ Passed | 0.4 s |
| `pagesNotOnServerReturnNotFound(String)[1]` | ✅ Passed | 0.2 s |
| `pagesNotOnServerReturnNotFound(String)[2]` | ✅ Passed | 0.3 s |
| `pagesNotOnServerReturnNotFound(String)[3]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[4]` | ✅ Passed | 0.2 s |
| `everyIconListedInManifestIsAvailable` | ✅ Passed | 1.1 s |
| `homePageRespondsWithinTwoSeconds` | ✅ Passed | 0.1 s |

### SauceDemoPostApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `postingToAnyPathIsNotAllowed(String)[1]` | ✅ Passed | 0.3 s |
| `postingToAnyPathIsNotAllowed(String)[2]` | ✅ Passed | 0.1 s |
| `postingToAnyPathIsNotAllowed(String)[3]` | ✅ Passed | 0.1 s |
| `refusedPostReturnsErrorPageWithoutSessionCookie` | ✅ Passed | 0.1 s |
| `postingLoginCredentialsIsNotAllowed` | ✅ Passed | 0.1 s |
| `postingCartContentsIsNotAllowed` | ✅ Passed | 0.1 s |

### LoginTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `logoutReturnsToLoginPage` | ✅ Passed | 5.8 s |
| `invalidCredentialsShowError` | ✅ Passed | 4.8 s |
| `lockedOutUserSeesLockedOutError` | ✅ Passed | 4.7 s |
| `standardUserIsRedirectedToInventory` | ✅ Passed | 4.7 s |
| `menuShowsLogoutLinkForLoggedInUser` | ✅ Passed | 5.3 s |

### ProductTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `clickingProductNameOpensItsDetailPage` | ✅ Passed | 4.9 s |
| `addingProductFromDetailPageShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.4 s |
| `sortByPriceLowToHigh` | ✅ Passed | 4.9 s |
| `sortByPriceHighToLow` | ✅ Passed | 4.9 s |
| `sortByNameAToZ` | ✅ Passed | 5.1 s |
| `sortByNameZToA` | ✅ Passed | 5.0 s |

### CartTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `removingProductEmptiesCart` | ✅ Passed | 5.0 s |
| `cartShowsCheckoutAndContinueShoppingButtons` | ✅ Passed | 4.9 s |
| `continueShoppingAfterRemovingShowsAddToCartAgain` | ✅ Passed | 5.1 s |
| `addingProductShowsRemoveButtonAndCartBadge` | ✅ Passed | 4.8 s |
| `cartShowsAddedProductDetails` | ✅ Passed | 5.0 s |

### CheckoutTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `validInformationLeadsToOverview` | ✅ Passed | 5.5 s |
| `emptyFirstNameShowsError` | ✅ Passed | 5.4 s |
| `longFirstNameIsAccepted` | ✅ Passed | 6.1 s |
| `emptyZipShowsError` | ✅ Passed | 5.5 s |
| `longZipIsAccepted` | ✅ Passed | 5.3 s |
| `emptyLastNameShowsError` | ✅ Passed | 5.3 s |
| `checkoutInformationPageShowsFormAndButtons` | ✅ Passed | 5.1 s |

### E2ETests (UI)

| Test | Status | Duration |
|---|---|---:|
| `standardUserCanCompletePurchase` | ✅ Passed | 5.8 s |

_Generated from the Surefire XML results of the run above. The full HTML version is in [`surefire.html`](surefire.html) (open it locally in a browser)._
