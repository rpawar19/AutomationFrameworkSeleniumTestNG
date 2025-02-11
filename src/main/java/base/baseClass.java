package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.loginPage;
import reports.ExtentUtility;
import config.configReader;

import java.time.Duration;
import java.util.List;

public class baseClass extends configReader {

    public static WebDriver driver;
    public static loginPage loginpage;
    public static ExtentUtility extent;

    public void initDriver(String browser){
        init();
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else{
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }
    public void init(){
        loginpage = new loginPage();
        extent = new ExtentUtility();
    }
    public WebElement getWebElement(By by){
        return driver.findElement((by));
    }
    public List<WebElement> getWebElements(By by){
        return driver.findElements((by));
    }
    public void enterText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }

    public void clickUsingJS(WebElement ele){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",ele);
    }
    public void waitTillElementVisible(By by, int number ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(number));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitTillElementClickable(By by, int number ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(number));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public void pause(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
