package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestData;

public class LoginTest extends BaseTest {
	

	
	@Test(dataProvider = "loginData",
		      dataProviderClass = TestData.class,
		      retryAnalyzer = utils.Retry.class)
    public void loginTest(String tenant, String username, String password, String expected) {

		getDriver().get(ConfigReader.get("url"));
        LoginPage loginPage = new LoginPage(getDriver());

        // 🔹 Perform login
        loginPage.login(tenant, username, password);

        // 🔹 Validation
        if (expected.equalsIgnoreCase("invalid")) {

            String error = loginPage.getErrorMessage();

            Assert.assertFalse(error.isEmpty(),
                    "❌ Error message not displayed for invalid login");

        } else if (expected.equalsIgnoreCase("valid")) {

            // ✅ Check NO error message appears
            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();

            Assert.assertFalse(isErrorDisplayed,
                    "❌ Error message displayed for valid login");
        }
    }
}