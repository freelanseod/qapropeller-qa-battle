package ui.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class PrintNameListener implements ITestListener {

    @Override
    public void onTestStart(final ITestResult result) {
        System.out.println("TEST :: " + result.getTestClass().getName() + "#" + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(final ITestResult result) {

    }

    @Override
    public void onTestFailure(final ITestResult result) {

    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        System.out.println("TEST SKIPPED :: " + result.getTestClass().getName() + "#" + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {

    }

    @Override
    public void onStart(final ITestContext context) {

    }

    @Override
    public void onFinish(final ITestContext context) {

    }

}
