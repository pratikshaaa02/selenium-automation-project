package reports;

import org.openqa.selenium.WebDriver;

import base.BaseTest;

import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentLogger {

    public static void info(String message) {

        if (ExtentManager.getTest() != null) {

            WebDriver driver = BaseTest.getDriver();

            // 📸 Capture screenshot for every step
            String path = ScreenshotUtil.captureScreenshot(driver, "step");

            ExtentManager.getTest().info(message,
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        } else {
            System.out.println("LOG: " + message);
        }
    }
}