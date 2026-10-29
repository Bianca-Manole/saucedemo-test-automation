# Test Report — SauceDemo Regression

**Result: ✅ ALL TESTS PASSED**

| Total | Passed | Failed | Skipped | Success rate | Duration |
|---:|---:|---:|---:|---:|---:|
| 25 | 25 | 0 | 0 | 100% | 133.4 s |

## Environment

| | |
|---|---|
| Run date | 2026-10-29 |
| Command | `mvn clean test` (suite: `RegressionSuite`) |
| Application | https://www.saucedemo.com/ |
| Browser | Chrome 156 |
| Java | 25.0.2 |
| OS | Windows 11 |
| Frameworks | Selenium 4.30.0, JUnit 5.14.0 |

## Summary per test class

| Test class | Tests | Passed | Failed | Duration |
|---|---:|---:|---:|---:|
| LoginTests | 5 | 5 | 0 | 26.2 s |
| ProductTests | 6 | 6 | 0 | 29.8 s |
| CartTests | 5 | 5 | 0 | 25.1 s |
| CheckoutTests | 8 | 8 | 0 | 46.2 s |
| E2ETests | 1 | 1 | 0 | 6.0 s |

## Test cases

### LoginTests

| Test | Status | Duration |
|---|---|---:|
| `logoutReturnsToLoginPage` | ✅ Passed | 6.2 s |
| `invalidCredentialsShowError` | ✅ Passed | 4.9 s |
| `lockedOutUserSeesLockedOutError` | ✅ Passed | 4.8 s |
| `standardUserIsRedirectedToInventory` | ✅ Passed | 4.8 s |
| `menuShowsLogoutLinkForLoggedInUser` | ✅ Passed | 5.4 s |

### ProductTests

| Test | Status | Duration |
|---|---|---:|
| `clickingProductNameOpensItsDetailPage` | ✅ Passed | 5.0 s |
| `addingProductFromDetailPageShowsRemoveButtonAndCartBadge` | ✅ Passed | 4.9 s |
| `sortByPriceLowToHigh` | ✅ Passed | 5.0 s |
| `sortByPriceHighToLow` | ✅ Passed | 4.9 s |
| `sortByNameAToZ` | ✅ Passed | 4.9 s |
| `sortByNameZToA` | ✅ Passed | 5.1 s |

### CartTests

| Test | Status | Duration |
|---|---|---:|
| `removingProductEmptiesCart` | ✅ Passed | 5.0 s |
| `cartShowsCheckoutAndContinueShoppingButtons` | ✅ Passed | 5.0 s |
| `continueShoppingAfterRemovingShowsAddToCartAgain` | ✅ Passed | 5.1 s |
| `addingProductShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.0 s |
| `cartShowsAddedProductDetails` | ✅ Passed | 5.1 s |

### CheckoutTests

| Test | Status | Duration |
|---|---|---:|
| `validInformationLeadsToOverview` | ✅ Passed | 8.2 s |
| `firstNameBelowMaxLengthIsAccepted` | ✅ Passed | 5.4 s |
| `zipAtMaxLengthIsAccepted` | ✅ Passed | 5.4 s |
| `emptyFirstNameShowsError` | ✅ Passed | 5.3 s |
| `firstNameAtMaxLengthIsAccepted` | ✅ Passed | 6.0 s |
| `emptyZipShowsError` | ✅ Passed | 5.4 s |
| `emptyLastNameShowsError` | ✅ Passed | 5.4 s |
| `checkoutInformationPageShowsFormAndButtons` | ✅ Passed | 5.0 s |

### E2ETests

| Test | Status | Duration |
|---|---|---:|
| `standardUserCanCompletePurchase` | ✅ Passed | 6.0 s |

_Generated from the Surefire XML results of the run above. The full HTML version is in [`surefire.html`](surefire.html) (open it locally in a browser)._
