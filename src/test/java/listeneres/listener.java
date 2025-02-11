package listeneres;

import base.baseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentUtility;
import utils.LoggerUtil;
import utils.screenCapture;

import static com.aventstack.extentreports.Status.PASS;


public class listener extends baseClass implements ITestListener {
    private static ExtentReports extent = ExtentUtility.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    // This method will be invoked when a test is started
    @Override
    public void onStart(org.testng.ITestContext context) {
        logger.info("Test suite started: " + context.getName());
    }

    // This method will be invoked before each test method is executed
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: " + result.getName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        testThread.set(extentTest);
        initDriver(browser);
    }

    // This method will be invoked when a test is passed
    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info("Test passed: " + result.getName());
        testThread.get().log(PASS, "Test Passed: " + result.getMethod().getMethodName());

    }
    // This method will be invoked when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName(), result.getThrowable());
        String screenshotPath = screenCapture.captureScreenShot(driver, result.getMethod().getMethodName());
        try {
            testThread.get().fail("Test Failed: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            testThread.get().fail("Test Failed, but screenshot could not be attached: " + e.getMessage());
        }
    }

    // This method will be invoked when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName());
        testThread.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    // This method will be invoked after all tests have finished executing
    @Override
    public void onFinish(org.testng.ITestContext context) {
        logger.info("Test suite finished: " + context.getName());
        ExtentUtility.flush();
        driver.quit();
    }

}
