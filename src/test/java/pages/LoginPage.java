package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.BasePage;
import reports.ExtentLogger; // ✅ IMPORT THIS
import utils.WaitUtils;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By tenantField = By.id("tenantInput");
    By switchTenantBtn = By.xpath("//*[@id=\"switchTenant\"]");  
    By tenantInput = By.xpath("//*[@id=\"tenantInput\"]");
    By saveTenantBtn = By.xpath("//button[contains(text(),'Save')]");

    By usernameInput = By.xpath("//*[@id=\"userNameInput\"]");
    By passwordInput = By.xpath("//*[@id=\"passwordInput\"]");
    By signInBtn = By.xpath("//*[@id=\"sendRequest\"]");
    
    By errorMessage = By.xpath("//*[contains(text(),'Invalid') or contains(@class,'error')]");

    public void login(String tenant, String username, String password) {

        // ✅ Business log only
        ExtentLogger.info("Starting Login Process");

        String currentTenant = driver.findElement(tenantField).getAttribute("value");

        // ✅ Tenant handling
        if (currentTenant == null || currentTenant.isEmpty() || !currentTenant.equalsIgnoreCase(tenant)) {

            ExtentLogger.info("Switching Tenant to: " + tenant);

            click(switchTenantBtn, "Switch Tenant button");
            waitForVisibility(tenantField, "Tenant field");

            type(tenantField, tenant, "Tenant field");

            click(saveTenantBtn, "Save Tenant button");

            WaitUtils.waitForInvisibility(driver, saveTenantBtn);

            ExtentLogger.info("Tenant selected successfully");
        } else {
            ExtentLogger.info("Tenant already selected: " + currentTenant);
        }

        // ✅ Login steps
        waitForVisibility(usernameInput, "Username field");

        ExtentLogger.info("Entering login credentials");

        type(usernameInput, username, "Username field");
        type(passwordInput, password, "Password field");

        click(signInBtn, "Sign In button");

        ExtentLogger.info("Login action performed");
    }
    
    public String getErrorMessage() {

        try {
            return driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isErrorMessageDisplayed() {

        try {
            return driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).isDisplayed();
        } catch (Exception e) {
            return false; // ✅ No error → login successful
        }
        
        //test
    }
}