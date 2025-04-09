package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CheckoutPage checkoutPage;
    protected ShoppingCartPage shoppingCartPage;
    protected OverviewPage overviewPage;
    protected OrderPage orderPage;
    protected Commons commons;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                ConfigReader.getInt("implicit.wait")
        ));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        System.out.println("Apertura browser");

        loginPage = new LoginPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        inventoryPage = new InventoryPage(driver);
        orderPage = new OrderPage(driver);
        commons = new Commons(driver, 10);
    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}