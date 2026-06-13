package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    WebDriver driver;

    private static final By ITEM_LIST = By.xpath("//div[@class='inventory_list']");
    private static final By FILTER = By.xpath("//select[@class='product_sort_container']");
    static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    static final By ITEM_DESCRIPTION = By.cssSelector("[data-test='inventory-item-desc']");
    static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");

    public static final By BACKPACK_ADD_TO_CART_BUTTON = By.id("add-to-cart-sauce-labs-backpack");
    public static final By BACKPACK_REMOVE_BUTTON = By.id("remove-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemListDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_LIST));
        return driver.findElement(ITEM_LIST).isDisplayed();
    }

    public void clickItemByName(String productName) {
        List<WebElement> items = driver.findElements(ITEM_NAME);
        items.stream()
                .filter(item -> item.getText().equals(productName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void addToCart(By addToCartButton) {
        driver.findElement(addToCartButton).click();
    }

    public boolean isButtonDisplayed(By button) {
        List<WebElement> elements = driver.findElements(button);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public void filterBy(String filterOption) {
        Select filter = new Select(driver.findElement(FILTER));
        filter.selectByValue(filterOption);
    }

    public boolean isNameSortedAZ() {
        List<WebElement> items = driver.findElements(ITEM_NAME);
        List<String> names = items.stream().map(WebElement::getText).toList();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        return names.equals(sorted);
    }

    public boolean isNameSortedZA() {
        List<WebElement> items = driver.findElements(ITEM_NAME);
        List<String> names = items.stream().map(WebElement::getText).toList();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());
        return names.equals(sorted);
    }

    public boolean isPriceSortedLowHigh() {
        List<WebElement> items = driver.findElements(ITEM_PRICE);
        List<Double> prices = items.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        return prices.equals(sorted);
    }

    public boolean isPriceSortedHighLow() {
        List<WebElement> items = driver.findElements(ITEM_PRICE);
        List<Double> prices = items.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        return prices.equals(sorted);
    }
}
