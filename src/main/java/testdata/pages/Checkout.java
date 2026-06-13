package testdata.pages;

import org.openqa.selenium.By;

public class Checkout {

    public final static By CHECKOUT_TITLE_ONE = By.className("title");
    public final static String CHECKOUT_TITLE_ONE_TEXT = "Checkout: Your Information";
    public final static By CHECKOUT_TITLE_TWO = By.cssSelector("[data-test='title']");
    public final static String CHECKOUT_TITLE_TWO_TEXT = "Checkout: Overview";
    public final static By CHECKOUT_COMPLETE_TITLE = By.className("title");
    public final static String CHECKOUT_COMPLETE_TITLE_TEXT = "Checkout: Complete!";

    public final static By COMPLETE_MESSAGE = By.className("complete-header");
    public final static String COMPLETE_MESSAGE_TEXT = "Thank you for your order!";

    public final static By CHECKOUT_FORM = By.className("checkout_info");
    public final static By FIRST_NAME_INPUT = By.id("first-name");
    public final static By LAST_NAME_INPUT = By.id("last-name");
    public final static By POSTAL_CODE_INPUT = By.id("postal-code");
    public final static By CANCEL_BUTTON = By.id("cancel");
    public final static By CONTINUE_BUTTON = By.id("continue");
    public final static By BACK_HOME_BUTTON = By.id("back-to-products");

    public final static By ERROR = By.cssSelector("[data-test='error']");
    public final static String ERROR_FIRST_NAME_TEXT = "Error: First Name is required";
    public final static String ERROR_LAST_NAME_TEXT = "Error: Last Name is required";
    public final static String ERROR_POSTAL_CODE_TEXT = "Error: Postal Code is required";

    public final static By PAYMENT_INFORMATION = By.cssSelector("[data-test='payment-info-label']");
    public final static By SHIPPING_INFORMATION = By.cssSelector("[data-test='shipping-info-label']");
    public final static By PRICE_TOTAL = By.cssSelector("[data-test='total-info-label']");
    public final static By ITEM_TOTAL = By.cssSelector("[data-test='subtotal-label']");
    public final static By TAX = By.cssSelector("[data-test='tax-label']");
    public final static By TOTAL = By.cssSelector("[data-test='total-label']");
    public final static By FINISH_BUTTON = By.id("finish");

    public final static By PAYMENT_METHOD = By.cssSelector("[data-test='payment-info-value']");
    public final static String PAYMENT_METHOD_TEXT = "SauceCard #31337";

    public final static By SHIPPING_METHOD = By.cssSelector("[data-test='shipping-info-value']");
    public final static String SHIPPING_METHOD_TEXT = "Free Pony Express Delivery!";

}
