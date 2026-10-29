# Test Report — Full Regression (API + UI)

**Result: ✅ ALL TESTS PASSED**

| Total | Passed | Failed | Skipped | Success rate | Duration |
|---:|---:|---:|---:|---:|---:|
| 40 | 40 | 0 | 0 | 100% | 157.3 s |

## Environment

| | |
|---|---|
| Run date | 2026-10-29 |
| Command | `mvn clean test` (suite: `RegressionSuite`) |
| UI application | https://www.saucedemo.com/ |
| API | https://restful-booker.herokuapp.com |
| Browser | Chrome 156 |
| Java | 25.0.2 |
| OS | Windows 11 |
| Frameworks | Selenium 4.30.0, RestAssured 5.5.6, JUnit 5.14.0 |

## Summary per test class

| Layer | Test class | Tests | Passed | Failed | Duration |
|---|---|---:|---:|---:|---:|
| API | AuthApiTests | 2 | 2 | 0 | 4.3 s |
| API | BookingApiTests | 13 | 13 | 0 | 19.7 s |
| UI | LoginTests | 5 | 5 | 0 | 26.1 s |
| UI | ProductTests | 6 | 6 | 0 | 31.2 s |
| UI | CartTests | 5 | 5 | 0 | 25.8 s |
| UI | CheckoutTests | 8 | 8 | 0 | 44.1 s |
| UI | E2ETests | 1 | 1 | 0 | 6.2 s |

## Test cases

### AuthApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `validCredentialsReturnToken` | ✅ Passed | 3.8 s |
| `invalidCredentialsAreRejected` | ✅ Passed | 0.4 s |

### BookingApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `createBookingWithMissingFieldsIsRejected` | ✅ Passed | 0.4 s |
| `apiIsUp` | ✅ Passed | 0.4 s |
| `updateBookingReplacesAllFields` | ✅ Passed | 2.1 s |
| `deleteWithoutTokenIsForbidden` | ✅ Passed | 2.0 s |
| `bookingResponseMatchesJsonSchema` | ✅ Passed | 2.0 s |
| `getNonExistingBookingReturnsNotFound` | ✅ Passed | 0.4 s |
| `createBookingReturnsIdAndSameData` | ✅ Passed | 1.2 s |
| `bookingsCanBeFilteredByName` | ✅ Passed | 1.6 s |
| `bookingListIsNotEmpty` | ✅ Passed | 1.7 s |
| `deleteBookingRemovesIt` | ✅ Passed | 2.1 s |
| `updateWithoutTokenIsForbidden` | ✅ Passed | 2.0 s |
| `partialUpdateChangesOnlyGivenFields` | ✅ Passed | 2.0 s |
| `createdBookingCanBeRetrievedById` | ✅ Passed | 1.6 s |

### LoginTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `logoutReturnsToLoginPage` | ✅ Passed | 6.0 s |
| `invalidCredentialsShowError` | ✅ Passed | 4.9 s |
| `lockedOutUserSeesLockedOutError` | ✅ Passed | 4.9 s |
| `standardUserIsRedirectedToInventory` | ✅ Passed | 4.8 s |
| `menuShowsLogoutLinkForLoggedInUser` | ✅ Passed | 5.5 s |

### ProductTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `clickingProductNameOpensItsDetailPage` | ✅ Passed | 5.1 s |
| `addingProductFromDetailPageShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.6 s |
| `sortByPriceLowToHigh` | ✅ Passed | 5.0 s |
| `sortByPriceHighToLow` | ✅ Passed | 5.2 s |
| `sortByNameAToZ` | ✅ Passed | 5.1 s |
| `sortByNameZToA` | ✅ Passed | 5.2 s |

### CartTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `removingProductEmptiesCart` | ✅ Passed | 5.2 s |
| `cartShowsCheckoutAndContinueShoppingButtons` | ✅ Passed | 5.2 s |
| `continueShoppingAfterRemovingShowsAddToCartAgain` | ✅ Passed | 5.2 s |
| `addingProductShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.0 s |
| `cartShowsAddedProductDetails` | ✅ Passed | 5.2 s |

### CheckoutTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `validInformationLeadsToOverview` | ✅ Passed | 5.5 s |
| `firstNameBelowMaxLengthIsAccepted` | ✅ Passed | 5.7 s |
| `zipAtMaxLengthIsAccepted` | ✅ Passed | 5.5 s |
| `emptyFirstNameShowsError` | ✅ Passed | 5.5 s |
| `firstNameAtMaxLengthIsAccepted` | ✅ Passed | 5.5 s |
| `emptyZipShowsError` | ✅ Passed | 5.5 s |
| `emptyLastNameShowsError` | ✅ Passed | 5.6 s |
| `checkoutInformationPageShowsFormAndButtons` | ✅ Passed | 5.2 s |

### E2ETests (UI)

| Test | Status | Duration |
|---|---|---:|
| `standardUserCanCompletePurchase` | ✅ Passed | 6.2 s |

_Generated from the Surefire XML results of the run above. The full HTML version is in [`surefire.html`](surefire.html) (open it locally in a browser)._
