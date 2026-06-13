package testdata.pages;

import org.openqa.selenium.By;

public class Cart {

    public final static By TITLE = By.className("title");
    public final static String TITLE_TEXT = "Your Cart";

    public final static By QUANTITY = By.cssSelector("[data-test='cart-quantity-label']");
    public final static String QUANTITY_TEXT = "QTY";
    public final static By ITEM_QUANTITY = By.cssSelector("[data-test='item-quantity']");

    public final static By DESCRIPTION = By.cssSelector("[data-test='cart-desc-label']");
    public final static String DESCRIPTION_TEXT = "Description";

    public final static By CART_ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    public final static By CART_ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    public final static By CART_ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    public final static By REMOVE_BUTTON = By.cssSelector("button[id^='remove']");
    public final static By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    public final static By CHECKOUT_BUTTON = By.cssSelector("button[id^='checkout']");

}
