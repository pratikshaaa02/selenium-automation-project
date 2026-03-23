package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import reports.TestListener;

@Listeners(TestListener.class)
public class LoginPositiveTest extends BaseTest {

    @Test
    public void validLoginTest() {

    	LoginPage loginPage = new LoginPage(getDriver());

        // ✅ Use VALID credentials
        loginPage.login("Rutvi", "Rutvi", "Abcd@1234567");

        // ✅ Validation (example)
       // Assert.assertTrue(!driver.getCurrentUrl().contains("login"),
       //         "Login failed - still on login page");
        Assert.assertTrue(!getDriver().getCurrentUrl().contains("login"),
                "Login failed - still on login page");
    }
}