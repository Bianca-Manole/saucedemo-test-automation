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
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        boolean headless = ConfigReader.getBoolean("headless");
        driver = createDriver(ConfigReader.get("browser"), headless);
        if (!headless) {
            driver.manage().window().maximize();
        }
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

    // Headless browsers have no screen to maximize into, so they get a fixed desktop-sized window.
    private static final String WINDOW_SIZE = "--window-size=1920,1080";

    private static WebDriver createDriver(String browser, boolean headless) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(chromeOptions(headless));
            case "firefox" -> new FirefoxDriver(firefoxOptions(headless));
            case "edge" -> new EdgeDriver(edgeOptions(headless));
            default -> throw new IllegalArgumentException("Unsupported browser '" + browser + "', use chrome, firefox or edge");
        };
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless", "--width=1920", "--height=1080");
        }
        return options;
    }

    private static EdgeOptions edgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new", WINDOW_SIZE);
        }
        return options;
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new", WINDOW_SIZE, "--disable-dev-shm-usage");
        }
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
