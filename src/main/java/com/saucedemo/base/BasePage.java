package com.saucedemo.base;

import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout")));
        // The element can be re-rendered between finding it and using it; retry instead of failing.
        this.wait.ignoring(StaleElementReferenceException.class);
        this.wait.ignoring(ElementClickInterceptedException.class);
    }

    protected void click(By locator) {
        wait.until(d -> {
            WebElement element = ExpectedConditions.elementToBeClickable(locator).apply(d);
            if (element == null) {
                return false;
            }
            element.click();
            return true;
        });
    }

    protected void type(By locator, String value) {
        wait.until(d -> {
            WebElement element = ExpectedConditions.visibilityOfElementLocated(locator).apply(d);
            if (element == null) {
                return false;
            }
            element.clear();
            element.sendKeys(value);
            return true;
        });
    }

    protected String textOf(By locator) {
        return wait.until(d -> {
            WebElement element = ExpectedConditions.visibilityOfElementLocated(locator).apply(d);
            return element == null ? null : element.getText();
        });
    }

    protected List<String> textsOf(By locator) {
        return wait.until(d -> {
            List<WebElement> elements = ExpectedConditions.visibilityOfAllElementsLocatedBy(locator).apply(d);
            return elements == null ? null : elements.stream().map(WebElement::getText).toList();
        });
    }

    protected void selectByValue(By locator, String value) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).selectByValue(value);
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isGone(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isAtUrl(String path) {
        String expected = ConfigReader.get("baseUrl") + path;
        return waitForUrl(url -> url.equals(expected));
    }

    protected boolean isAtUrlStartingWith(String path) {
        String expected = ConfigReader.get("baseUrl") + path;
        return waitForUrl(url -> url.startsWith(expected));
    }

    private boolean waitForUrl(Predicate<String> condition) {
        try {
            return wait.until(d -> condition.test(d.getCurrentUrl()));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
