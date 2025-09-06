package com.demoblaze.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoblaze.framework.base.DriverFactory;
import com.demoblaze.framework.constants.FrameworkConstants;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();
    private static final ExtentReports EXTENT = ExtentManager.get();

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = EXTENT.createTest(result.getMethod().getMethodName());
        TEST.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TEST.get().log(Status.FAIL, result.getThrowable());
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String destPath = FrameworkConstants.SCREENSHOT_DIR + result.getMethod().getMethodName() + "_" + stamp + ".png";
                File dest = new File(destPath);
                dest.getParentFile().mkdirs();
                Files.copy(src.toPath(), dest.toPath());
                TEST.get().addScreenCaptureFromPath(destPath);
            }
        } catch (Exception e) {
            TEST.get().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().log(Status.SKIP, result.getThrowable());
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) { onTestFailure(result); }
    @Override public void onStart(ITestContext context) {}
}