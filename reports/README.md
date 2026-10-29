# Test Report — Full Regression (API + UI)

**Result: ✅ ALL TESTS PASSED**

| Total | Passed | Failed | Skipped | Success rate | Duration |
|---:|---:|---:|---:|---:|---:|
| 57 | 57 | 0 | 0 | 100% | 157.5 s |

## Environment

| | |
|---|---|
| Run date | 2026-10-29 |
| Command | `mvn clean test` (suite: `RegressionSuite`) |
| UI application | https://www.saucedemo.com/ |
| API | https://www.saucedemo.com/ (GET/POST over HTTP), https://restful-booker.herokuapp.com (CRUD) |
| Browser | Chrome 156 |
| Java | 25.0.2 |
| OS | Windows 11 |
| Frameworks | Selenium 4.30.0, RestAssured 5.5.6, JUnit 5.14.0 |

## Summary per test class

| Layer | Test class | Tests | Passed | Failed | Duration |
|---|---|---:|---:|---:|---:|
| API | SauceDemoGetApiTests | 11 | 11 | 0 | 4.7 s |
| API | SauceDemoPostApiTests | 6 | 6 | 0 | 1.0 s |
| API | AuthApiTests | 2 | 2 | 0 | 1.9 s |
| API | BookingApiTests | 13 | 13 | 0 | 19.2 s |
| UI | LoginTests | 5 | 5 | 0 | 26.1 s |
| UI | ProductTests | 6 | 6 | 0 | 30.0 s |
| UI | CartTests | 5 | 5 | 0 | 25.5 s |
| UI | CheckoutTests | 8 | 8 | 0 | 43.3 s |
| UI | E2ETests | 1 | 1 | 0 | 5.8 s |

## Test cases

### SauceDemoGetApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `manifestDescribesTheSwagLabsApp` | ✅ Passed | 2.3 s |
| `robotsTxtAllowsAllCrawlers` | ✅ Passed | 0.1 s |
| `manifestMatchesJsonSchema` | ✅ Passed | 0.5 s |
| `homePageIsServedAsHtml` | ✅ Passed | 0.1 s |
| `javascriptBundleReferencedByHomePageIsServed` | ✅ Passed | 0.3 s |
| `pagesNotOnServerReturnNotFound(String)[1]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[2]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[3]` | ✅ Passed | 0.1 s |
| `pagesNotOnServerReturnNotFound(String)[4]` | ✅ Passed | 0.1 s |
| `everyIconListedInManifestIsAvailable` | ✅ Passed | 0.7 s |
| `homePageRespondsWithinTwoSeconds` | ✅ Passed | 0.1 s |

### SauceDemoPostApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `postingToAnyPathIsNotAllowed(String)[1]` | ✅ Passed | 0.3 s |
| `postingToAnyPathIsNotAllowed(String)[2]` | ✅ Passed | 0.1 s |
| `postingToAnyPathIsNotAllowed(String)[3]` | ✅ Passed | 0.2 s |
| `refusedPostReturnsErrorPageWithoutSessionCookie` | ✅ Passed | 0.1 s |
| `postingLoginCredentialsIsNotAllowed` | ✅ Passed | 0.1 s |
| `postingCartContentsIsNotAllowed` | ✅ Passed | 0.2 s |

### AuthApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `validCredentialsReturnToken` | ✅ Passed | 1.5 s |
| `invalidCredentialsAreRejected` | ✅ Passed | 0.4 s |

### BookingApiTests (API)

| Test | Status | Duration |
|---|---|---:|
| `createBookingWithMissingFieldsIsRejected` | ✅ Passed | 0.4 s |
| `apiIsUp` | ✅ Passed | 0.4 s |
| `updateBookingReplacesAllFields` | ✅ Passed | 2.1 s |
| `deleteWithoutTokenIsForbidden` | ✅ Passed | 2.0 s |
| `bookingResponseMatchesJsonSchema` | ✅ Passed | 1.6 s |
| `getNonExistingBookingReturnsNotFound` | ✅ Passed | 0.4 s |
| `createBookingReturnsIdAndSameData` | ✅ Passed | 1.2 s |
| `bookingsCanBeFilteredByName` | ✅ Passed | 1.6 s |
| `bookingListIsNotEmpty` | ✅ Passed | 1.9 s |
| `deleteBookingRemovesIt` | ✅ Passed | 2.0 s |
| `updateWithoutTokenIsForbidden` | ✅ Passed | 2.0 s |
| `partialUpdateChangesOnlyGivenFields` | ✅ Passed | 2.0 s |
| `createdBookingCanBeRetrievedById` | ✅ Passed | 1.6 s |

### LoginTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `logoutReturnsToLoginPage` | ✅ Passed | 6.0 s |
| `invalidCredentialsShowError` | ✅ Passed | 5.1 s |
| `lockedOutUserSeesLockedOutError` | ✅ Passed | 4.9 s |
| `standardUserIsRedirectedToInventory` | ✅ Passed | 4.8 s |
| `menuShowsLogoutLinkForLoggedInUser` | ✅ Passed | 5.4 s |

### ProductTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `clickingProductNameOpensItsDetailPage` | ✅ Passed | 4.9 s |
| `addingProductFromDetailPageShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.0 s |
| `sortByPriceLowToHigh` | ✅ Passed | 5.1 s |
| `sortByPriceHighToLow` | ✅ Passed | 5.0 s |
| `sortByNameAToZ` | ✅ Passed | 5.0 s |
| `sortByNameZToA` | ✅ Passed | 5.1 s |

### CartTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `removingProductEmptiesCart` | ✅ Passed | 5.0 s |
| `cartShowsCheckoutAndContinueShoppingButtons` | ✅ Passed | 5.0 s |
| `continueShoppingAfterRemovingShowsAddToCartAgain` | ✅ Passed | 5.3 s |
| `addingProductShowsRemoveButtonAndCartBadge` | ✅ Passed | 5.0 s |
| `cartShowsAddedProductDetails` | ✅ Passed | 5.2 s |

### CheckoutTests (UI)

| Test | Status | Duration |
|---|---|---:|
| `validInformationLeadsToOverview` | ✅ Passed | 5.5 s |
| `firstNameBelowMaxLengthIsAccepted` | ✅ Passed | 5.7 s |
| `zipAtMaxLengthIsAccepted` | ✅ Passed | 5.5 s |
| `emptyFirstNameShowsError` | ✅ Passed | 5.4 s |
| `firstNameAtMaxLengthIsAccepted` | ✅ Passed | 5.5 s |
| `emptyZipShowsError` | ✅ Passed | 5.3 s |
| `emptyLastNameShowsError` | ✅ Passed | 5.2 s |
| `checkoutInformationPageShowsFormAndButtons` | ✅ Passed | 5.1 s |

### E2ETests (UI)

| Test | Status | Duration |
|---|---|---:|
| `standardUserCanCompletePurchase` | ✅ Passed | 5.8 s |

_Generated from the Surefire XML results of the run above. The full HTML version is in [`surefire.html`](surefire.html) (open it locally in a browser)._
