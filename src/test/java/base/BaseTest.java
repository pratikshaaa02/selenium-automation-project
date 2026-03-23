package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader; // ✅ IMPORT

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static boolean isParallel = false;

    // ❌ REMOVE HARDCODED URL
    // private static final String URL = "...";

    // ===============================
    // ✅ SEQUENTIAL MODE
    // ===============================
    @BeforeClass
    public void setUpClass() {
        if (!isParallel) {
            initDriver();
        }
    }

    // ===============================
    // ⚡ PARALLEL MODE
    // ===============================
    @BeforeMethod
    public void setUpMethod() {
        if (isParallel) {
            initDriver();
        }
    }

    // ===============================
    // 🔥 DRIVER INITIALIZATION (COMMON)
    // ===============================
    public void initDriver() {

        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true);

            driver.set(new FirefoxDriver(options));

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // ✅ URL from config file
        getDriver().get(ConfigReader.get("url"));
    }

    // ===============================
    public static WebDriver getDriver() {
        return driver.get();
    }

    // ===============================
    @AfterMethod
    public void tearDownMethod() {
        if (isParallel) {
            getDriver().quit();
            driver.remove();
        }
    }

    // ===============================
    @AfterClass
    public void tearDownClass() {
        if (!isParallel) {
            getDriver().quit();
            driver.remove();
        }
    }
}