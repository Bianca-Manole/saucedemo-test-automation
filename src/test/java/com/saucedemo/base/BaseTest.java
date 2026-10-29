package com.saucedemo.base;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.CheckoutStepTwoPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.ItemPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.components.Header;
import com.saucedemo.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import testdata.classes.User;

import java.util.Map;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {
    protected WebDriver driver;

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected ItemPage itemPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CheckoutCompletePage checkoutCompletePage;
    protected Header header;

    protected final User standardUser = User.fromConfig();

    @BeforeEach
    public void setUp() {
        driver = createDriver(ConfigReader.get("browser"));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        header = new Header(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void loginAsStandardUser() {
        loginPage.login(standardUser);
    }

    private static WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(chromeOptions());
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser in config.properties: " + browser);
        };
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "ignore-certificate-errors",
                "disable-infobars",
                "disable-popup-blocking",
                "disable-default-apps",
                "no-first-run",
                "no-default-browser-check",
                "--disable-search-engine-choice-screen",
                "disable-notifications",
                "disable-extensions");
        // Stops Chrome's save-password and leaked-password popups from covering the page after login.
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        return options;
    }
}
