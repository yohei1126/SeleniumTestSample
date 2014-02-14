package test1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GoogleTestByChrome {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        // Chromeの場合
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.binary",
                "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome");
        System.setProperty("webdriver.chrome.driver",
                "/Users/yohei/usr/chromedriver-2.9/chromedriver");
        driver = new ChromeDriver(capabilities);

        baseUrl = "https://www.google.co.jp/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testGoogle() throws Exception {
        driver.get(baseUrl + "/");
        String browserName = ((HasCapabilities) driver).getCapabilities()
                .getBrowserName();

        if (browserName.equals("firefox")) {
            driver.findElement(By.id("lst-ib")).clear();
            driver.findElement(By.id("lst-ib")).sendKeys("Selenium");
            driver.findElement(By.name("btnK")).click();
        } else {
            driver.findElement(By.name("q")).clear();
            driver.findElement(By.name("q")).sendKeys("Selenium");
        }

        driver.findElement(By.linkText("Selenium - Web Browser Automation"))
                .click();

        // Wait for page loading
        Thread.sleep(1000);

        File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotFilePath = "./img/screenShot" + (new Date()).getTime() + ".jpg";
        FileHandler.copy(screenShotFile, new File(screenShotFilePath));

        String actual = driver.findElement(By.cssSelector("#header li:nth-of-type(1)")).getText();
        assertEquals("タブ名の検証", "About", actual);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
