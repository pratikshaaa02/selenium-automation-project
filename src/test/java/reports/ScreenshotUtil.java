package reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String name) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // ✅ Unique name using timestamp
        String fileName = name + "_" + System.currentTimeMillis() + ".png";

        String dirPath = System.getProperty("user.dir") + "/test-output/screenshots/";
        String fullPath = dirPath + fileName;

        File directory = new File(dirPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File dest = new File(fullPath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ✅ Return relative path (important for Extent Report)
        return "test-output/screenshots/" + fileName;
    }
}