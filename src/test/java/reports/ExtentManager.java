package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private static ExtentReports extent = new ExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // ✅ Create test
    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    // ✅ GET test (THIS WAS MISSING ❌)
    public static ExtentTest getTest() {
        return test.get();
    }

    // (Optional) get extent
    public static ExtentReports getExtent() {
        return extent;
    }
}