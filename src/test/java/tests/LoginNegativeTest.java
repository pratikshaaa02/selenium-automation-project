package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import reports.TestListener;

@Listeners(TestListener.class)
public class LoginNegativeTest extends BaseTest {

	//@Test(enabled = false)
	@Test
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(getDriver());

        // ❌ Invalid credentials
        loginPage.login("Rutvi", "wrongUser", "Abcd@1234567890");

        // ✅ Validate error message
        String error = loginPage.getErrorMessage();

       /* Assert.assertTrue(error.toLowerCase().contains("invalid"),
                "Expected error message not displayed for invalid login");
                
                */
        
        Assert.assertTrue(
        	    loginPage.isErrorMessageDisplayed(),
        	    "Error message not displayed"
        	);
    }
}