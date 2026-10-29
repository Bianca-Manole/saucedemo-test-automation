# Test Report — Full Regression (API + UI)

**Result: ✅ ALL TESTS PASSED**

| Total | Passed | Failed | Skipped | Success rate | Duration |
|---:|---:|---:|---:|---:|---:|
| 42 | 42 | 0 | 0 | 100% | 133.8 s |

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
| API | SauceDemoGetApiTests | 11 | 11 | 0 | 5.7 s |
| API | SauceDemoPostApiTests | 6 | 6 | 0 | 1.0 s |
| UI | LoginTests | 5 | 5 | 0 | 25.9 s |
| UI | ProductTests | 6 | 6 | 0 | 29.5 s |
| UI | CartTests | 5 | 5 | 0 | 24.3 s |
| UI | CheckoutTests | 8 | 8 | 0 | 41.8 s |
| UI | E2ETests | 1 | 1 | 0 | 5.6 s |

## Test cases

### SauceDemoGetApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `manifestDescribesTheSwagLabsApp` | ✅ Passed | 2.4 s |
| `robotsTxtAllowsAllCrawlers` | ✅ Passed | 0.3 s |
| `manifestMatchesJsonSchema` | ✅ Passed | 0.6 s |
| `homePageIsServedAsHtml` | ✅ Passed | 0.1 s |
| `javascriptBundleReferencedByHomePageIsServed` | ✅ Passed | 0.4 s |
| `pagesNotOnServerReturnNotFound(String)[1]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[2]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[3]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[4]` | ✅ Passed | 0.1 s |
| `everyIconListedInManifestIsAvailable` | ✅ Passed | 1.1 s |
| `homePageRespondsWithinTwoSeconds` | ✅ Passed | 0.2 s |

### SauceDemoPostApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `postingToAnyPathIsNotAllowed(String)[1]` | ✅ Passed | 0.3 s |
| `postingToAnyPathIsNotAllowed(String)[2]` | ✅ Passed | 0.2 s |
| `postingToAnyPathIsNotAllowed(String)[3]` | ✅ Passed | 0.1 s |
| `refusedPostReturnsErrorPageWithoutSessionCookie` | ✅ Passed | 0.1 s |
| `postingLoginCredentialsIsNotAllowed` | ✅ Passed | 0.1 s |
| `postingCartContentsIsNotAllowed` | ✅ Passed | 0.1 s |

### LoginTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `logoutReturnsToLoginPage` | ✅ Passed | 5.7 s |
| `invalidCredentialsShowError` | ✅ Passed | 4.7 s |
| `lockedOutUserSeesLockedOutError` | ✅ Passed | 5.2 s |
| `standardUserIsRedirectedToInventory` | ✅ Passed | 5.1 s |
| `menuShowsLogoutLinkForLoggedInUser` | ✅ Passed | 5.2 s |

### ProductTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `clickingProductNameOpensItsDetailPage` | ✅ Passed | 4.9 s |
| `addingProductFromDetailPageShowsRemoveButtonAndCartBadge` | ✅ Passed | 4.9 s |
| `sortByPriceLowToHigh` | ✅ Passed | 5.0 s |
| `sortByPriceHighToLow` | ✅ Passed | 5.0 s |
| `sortByNameAToZ` | ✅ Passed | 4.9 s |
| `sortByNameZToA` | ✅ Passed | 4.9 s |

### CartTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `removingProductEmptiesCart` | ✅ Passed | 4.8 s |
| `cartShowsCheckoutAndContinueShoppingButtons` | ✅ Passed | 5.3 s |
| `continueShoppingAfterRemovingShowsAddToCartAgain` | ✅ Passed | 4.9 s |
| `addingProductShowsRemoveButtonAndCartBadge` | ✅ Passed | 4.7 s |
| `cartShowsAddedProductDetails` | ✅ Passed | 4.6 s |

### CheckoutTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `validInformationLeadsToOverview` | ✅ Passed | 5.7 s |
| `firstNameBelowMaxLengthIsAccepted` | ✅ Passed | 5.5 s |
| `zipAtMaxLengthIsAccepted` | ✅ Passed | 5.2 s |
| `emptyFirstNameShowsError` | ✅ Passed | 5.1 s |
| `firstNameAtMaxLengthIsAccepted` | ✅ Passed | 5.2 s |
| `emptyZipShowsError` | ✅ Passed | 5.1 s |
| `emptyLastNameShowsError` | ✅ Passed | 5.2 s |
| `checkoutInformationPageShowsFormAndButtons` | ✅ Passed | 4.8 s |

### E2ETests (UI)

| Test | Status | Duration |
|---|---|---:|
| `standardUserCanCompletePurchase` | ✅ Passed | 5.6 s |

_Generated from the Surefire XML results of the run above. The full HTML version is in [`surefire.html`](surefire.html) (open it locally in a browser)._
