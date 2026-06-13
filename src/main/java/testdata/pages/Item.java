package testdata.pages;

import org.openqa.selenium.By;

public class Item {

    public final static By BACK_TO_PRODUCTS = By.id("back-to-products");
    public final static By ADD_TO_CART = By.id("add-to-cart");
    public final static By REMOVE_FROM_CART = By.id("remove");
    //Sauce Labs Backpack
    public final static By BACKPACK_IMG = By.cssSelector("[data-test='item-sauce-labs-backpack-img']");

    //Sauce Labs Bike Light
    public final static By BIKE_LIGHT_IMG = By.cssSelector("[data-test='item-sauce-labs-bike-light-img']");

    //Sauce Labs Bolt T-Shirt
    public final static By BOLT_T_SHIRT_IMG = By.cssSelector("[data-test='item-sauce-labs-bolt-t-shirt-img']");

    //Sauce Labs Fleece Jacket
    public final static By FLEECE_JACKET_IMG = By.cssSelector("[data-test='item-sauce-labs-fleece-jacket-img']");

    //Sauce Labs Onesie
    public final static By ONESIE_IMG = By.cssSelector("[data-test='item-sauce-labs-onesie-img']");

    //Test.allTheThings() T-Shirt (Red)
    public final static By T_SHIRT_RED_IMG = By.cssSelector("[data-test='item-test.allthethings()-t-shirt-(red)-img']");
}
