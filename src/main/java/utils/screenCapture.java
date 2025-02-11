package utils;

import base.baseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenCapture extends baseClass {

    public static String captureScreenShot(WebDriver driver,String testName){
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenShots/" + testName+dateName+".JPEG";
        File destination = new File(path);
        try{
            FileUtils.copyFile(src,destination);
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
//    public static String captureScreenshot(WebDriver driver, String screenshotName) {
//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
//        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(source, new File(destination));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return destination;
//    }
}
