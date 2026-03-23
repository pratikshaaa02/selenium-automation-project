package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reports.ExtentLogger;
import utils.WaitUtils; // ✅ IMPORT

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ CLICK with WaitUtils
    public void click(By locator, String elementName) {
        WaitUtils.waitForClick(driver, locator);
        driver.findElement(locator).click();
        ExtentLogger.info("Clicked on: " + elementName);
    }

    // ✅ TYPE with WaitUtils
    public void type(By locator, String text, String elementName) {

        WebElement element = WaitUtils.waitForElement(driver, locator);

        element.clear(); // ✅ CLEAR OLD VALUE
        element.sendKeys(text); // ✅ ENTER NEW VALUE

        ExtentLogger.info("Entered '" + text + "' into: " + elementName);
    }

    // ✅ WAIT with WaitUtils
    public void waitForVisibility(By locator, String elementName) {
        WaitUtils.waitForElement(driver, locator);
        ExtentLogger.info("Waiting for: " + elementName);
    }
}