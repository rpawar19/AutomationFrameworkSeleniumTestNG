package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentUtility {
    private static ExtentReports extentReport;
    private static ExtentSparkReporter extentReporter;

    public static ExtentReports getInstance(){
        if(extentReport==null){
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_"+timestamp+".html";
            extentReporter = new ExtentSparkReporter(reportPath);
            extentReporter.config().setDocumentTitle("Test Report");
            extentReporter.config().setReportName("Functional Testing");
            extentReporter.config().setTheme(Theme.STANDARD);
            extentReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            extentReport = new ExtentReports();
            extentReport.attachReporter(extentReporter);
        }
        return extentReport;
    }
    public static void flush() {
        extentReport.flush();
    }
}
