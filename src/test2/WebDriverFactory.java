package test2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

class WebDriverFactory {
    static WebDriver getFirefoxDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    static WebDriver getChromeDriver() {
        // Chromeの場合
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.binary",
                "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome");
        System.setProperty("webdriver.chrome.driver",
                "/Users/yohei/usr/chromedriver-2.9/chromedriver");
        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
