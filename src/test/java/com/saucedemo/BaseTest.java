package com.saucedemo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testdata.URL;
import testdata.classes.User;
import testdata.pages.Login;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    static LoginPage loginPage;
    static InventoryPage inventoryPage;
    static ItemPage itemPage;
    static CartPage cartPage;
    static CheckoutStepOnePage checkoutStepOnePage;
    static CheckoutStepTwoPage checkoutStepTwoPage;


    public User standardUser = new User("standard");
    public User lockedUser = new User("locked");
    public User invalidUser = new User("invalid");


    @BeforeAll
    public static void before() {
        ChromeOptions chromeOption = new ChromeOptions();

        chromeOption.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        chromeOption.addArguments("disable-infobars");
        chromeOption.addArguments("disable-popup-blocking");
        chromeOption.addArguments("disable-default-apps");
        chromeOption.addArguments("no-first-run");
        chromeOption.addArguments("no-default-browser-check");
        chromeOption.addArguments("--disable-search-engine-choice-screen");
        chromeOption.addArguments("start-maximized");
        chromeOption.addArguments("disable-notifications");
        chromeOption.addArguments("disable-extensions");
        chromeOption.addArguments("guest");


        driver = new ChromeDriver(chromeOption);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void beforeTests() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);


        driver.get(URL.MAIN);
        Assertions.assertEquals(Login.LOGO_TEXT, loginPage.getLogoText());
    }

    @AfterEach
    public void afterEach() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @AfterAll
    public static void after() {

        driver.quit();
    }
}
