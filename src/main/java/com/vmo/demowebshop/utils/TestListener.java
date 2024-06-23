package com.vmo.demowebshop.utils;

import com.vmo.demowebshop.common.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {

        Log.info("-----------------" + result.getName() + ": Started" + "-----------------");
    }

    public void onTestSuccess(ITestResult result) {
        Log.info("-----------------" + result.getName() + ": Passed" + "-----------------");
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        saveScreenShot(driver);

    }

    public void onTestFailure(ITestResult result) {
        Log.info( "-----------------" +
                result.getName() + ": Failed" + "-----------------");
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        saveScreenShot(driver);
    }

    @Attachment
    public static byte[] saveScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
