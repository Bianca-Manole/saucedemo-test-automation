package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testdata.pages.Inventory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoText() {
        return driver.findElement(Inventory.LOGO).getText();
    }

    public void clickMenu() {
        driver.findElement(Inventory.MENU_BUTTON).click();
    }

    public boolean isLogoutButtonDisplayed() {
        clickMenu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Inventory.LOGOUT_BUTTON));

        return driver.findElement(Inventory.LOGOUT_BUTTON).isDisplayed();
    }

    public boolean isItemListDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Inventory.ITEM_LIST));

        return driver.findElement(Inventory.ITEM_LIST).isDisplayed();
    }

    private WebElement findItem(String itemName) {
        List<WebElement> items = driver.findElements(Inventory.ITEM_NAME);

        return items.stream()
                .filter(item -> item.getText().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public boolean itemExists(String itemName) {
        return findItem(itemName) != null;
    }

    public void clickItemByName(String productName) {
        List<WebElement> items = driver.findElements(Inventory.ITEM_NAME);
        items.stream()
                .filter(item -> item.getText().equals(productName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getItemName() {
        return driver.findElement(Inventory.ITEM_NAME).getText();
    }

    public String getItemPrice() {
        return driver.findElement(Inventory.ITEM_PRICE).getText();
    }

    public String getItemDescription() {
        return driver.findElement(Inventory.ITEM_DESCRIPTION).getText();
    }

    public void addToCart(By addToCartButton) {
        driver.findElement(addToCartButton).click();
    }

    public void removeFromCart(By removeFromCartButton) {
        driver.findElement(removeFromCartButton).click();
    }

    public void filterBy(String filterOption) {
        Select filter = new Select(driver.findElement(Inventory.FILTER));
        filter.selectByValue(filterOption);
    }

    public String getFirstItemName() {
        return driver.findElements(Inventory.ITEM_NAME).get(0).getText();
    }
    public boolean isNameSortedAZ() {
        List<WebElement> items = driver.findElements(Inventory.ITEM_NAME);
        List<String> names = items.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        return names.equals(sorted);
    }
    public boolean isNameSortedZA() {
        List<WebElement> items = driver.findElements(Inventory.ITEM_NAME);
        List<String> names = items.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());
        return names.equals(sorted);
    }

    public boolean isPriceSortedLowHigh() {
        List<WebElement> items = driver.findElements(Inventory.ITEM_PRICE);
        List<Double> prices = items.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        return prices.equals(sorted);
    }


    public boolean isPriceSortedHighLow() {
        List<WebElement> items = driver.findElements(Inventory.ITEM_PRICE);
        List<Double> prices = items.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        return prices.equals(sorted);
    }

    public void clickCartButton() {
        driver.findElement(Inventory.CART_BUTTON_WITH_ITEMS).click();
    }

}
