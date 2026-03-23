package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 2; // retry 2 times

    @Override
    public boolean retry(ITestResult result) {

        if (count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}