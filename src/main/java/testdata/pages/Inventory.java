package testdata.pages;

import org.openqa.selenium.By;

public class Inventory {

    //LOGO
    public final static By LOGO = By.xpath("//div[contains(text(), 'Swag Labs')]");

    //TITLE
    public final static By TITLE = By.xpath("//span[@class='title']");

    //MENU BUTTON
    public final static By MENU_BUTTON = By.id("react-burger-menu-btn");

    //LOGOUT BUTTON
    public final static By LOGOUT_BUTTON = By.id("logout_sidebar_link");

    //ITEM LIST
    public final static By ITEM_LIST = By.xpath("//div[@class='inventory_list']");

    //CART BUTTON
    public final static By CART_BUTTON_EMPTY = By.id("shopping_cart_container");
    public final static By CART_BUTTON_WITH_ITEMS = By.cssSelector("[data-test='shopping-cart-badge']");


    //FILTER
    public final static By FILTER = By.xpath("//select[@class='product_sort_container']");
    //FILTER OPTIONS
    public final static String FILTER_AZ = "az";
    public final static String FILTER_ZA = "za";
    public final static String FILTER_LOHI = "lohi";
    public final static String FILTER_HILO = "hilo";


    //ITEM_SELECTORS
    public final static By INVENTORY_ITEMS = By.cssSelector("[data-test='inventory-item']");
    public static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    public final static By ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    public final static By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    public final static By ITEM_IMG = By.className("inventory_item_img");

    //INVENTORY_ADD_TO_CART_BUTTON
    public final static By BACKPACK_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-backpack");
    public final static By BIKE_LIGHT_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-bike-light");
    public final static By BOLT_T_SHIRT_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    public final static By FLEECE_JACKET_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-fleece-jacket");
    public final static By ONESIE_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-onesie");
    public final static By T_SHIRT_RED_INVENTORY_ADD_TO_CART_BUTTON = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");

    //REMOVE BUTTON
    public final static By BACKPACK_REMOVE_BUTTON = By.id("remove-sauce-labs-backpack");
    public final static By BIKE_LIGHT_REMOVE_BUTTON = By.id("remove-sauce-labs-bike-light");
    public final static By BOLT_T_SHIRT_REMOVE_BUTTON = By.id("remove-sauce-labs-bolt-t-shirt");
    public final static By FLEECE_JACKET_REMOVE_BUTTON = By.id("remove-sauce-labs-fleece-jacket");
    public final static By ONESIE_REMOVE_BUTTON = By.id("remove-sauce-labs-onesie");
    public final static By T_SHIRT_RED_REMOVE_BUTTON = By.id("remove-test.allthethings()-t-shirt-(red)");

    //_NAME
    public final static String BACKPACK_NAME = "Sauce Labs Backpack";
    public final static String BIKE_LIGHT_NAME = "Sauce Labs Bike Light";
    public final static String BOLT_T_SHIRT_NAME = "Sauce Labs Bolt T-Shirt";
    public final static String FLEECE_JACKET_NAME = "Sauce Labs Fleece Jacket";
    public final static String ONESIE_NAME = "Sauce Labs Onesie";
    public final static String T_SHIRT_RED_NAME = "Test.allTheThings() T-Shirt (Red)";

    //_DESCRIPTION
    public final static String BACKPACK_DESCRIPTION = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    public final static String BIKE_LIGHT_DESCRIPTION = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";
    public final static String BOLT_T_SHIRT_DESCRIPTION = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    public final static String FLEECE_JACKET_DESCRIPTION = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
    public final static String ONESIE_DESCRIPTION = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
    public final static String T_SHIRT_RED_DESCRIPTION = "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.";

    //_PRICE
    public final static String BACKPACK_PRICE = "$29.99";
    public final static String BIKE_LIGHT_PRICE = "$9.99";
    public final static String BOLT_T_SHIRT_PRICE = "$15.99";
    public final static String FLEECE_JACKET_PRICE = "$49.99";
    public final static String ONESIE_PRICE = "$7.99";
    public final static String T_SHIRT_RED_PRICE = "$15.99";

    //_IMG
    public final static By BACKPACK_IMG_ID = By.id("item_4_img_link");
    public final static By BIKE_LIGHT_IMG_ID = By.id("item_0_img_link");
    public final static By BOLT_T_SHIRT_IMG_ID = By.id("item_1_img_link");
    public final static By FLEECE_JACKET_IMG_ID = By.id("item_5_img_link");
    public final static By ONESIE_IMG_ID = By.id("item_2_img_link");
    public final static By T_SHIRT_RED_IMG_ID = By.id("item_3_img_link");

}
