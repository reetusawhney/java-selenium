package com.wipro.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter reporter=new ExtentSparkReporter("ExtentReport.html");
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String failedtest = result.getName();
        String screenshotDir= "./screenshot/";
        ITestListener.super.onTestFailure(result);
        try {
            takeScreenshot(result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extent.attachReporter(reporter);
        extent.createTest(failedtest)
                .addScreenCaptureFromPath(screenshotDir + failedtest + ".png")
                .log(Status.FAIL, result.getThrowable())
                .info(result.getMethod().getDescription());
        extent.flush();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
