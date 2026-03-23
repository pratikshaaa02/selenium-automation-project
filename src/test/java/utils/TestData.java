package utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {

            // tenant, username, password, expectedResult
            {"Rutvi", "wrongUser", "wrongPass", "invalid"},
            {"Rutvi", "Rutvi", "Abcd@1234567", "valid"}

        };
    }
}