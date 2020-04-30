package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.Constants;

import java.util.concurrent.TimeUnit;

/**
 * WebDriverManager automatically recognizes installed browser,
 * it's version and downloads driver for it
 */
public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = setupChromeDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Constants.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors")
                .addArguments("start-maximized")
                .addArguments("enable-automation")
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-browser-side-navigation")
                .addArguments("--disable-gpu");
        return new ChromeDriver(options);
    }
}
