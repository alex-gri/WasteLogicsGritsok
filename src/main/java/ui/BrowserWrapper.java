package ui;

import driver.DriverSingleton;
import logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * This class allows to access WebDriver from anywhere
 * and wraps raw selenium methods to make them filled with logs
 * or combined in the specific way
 */
public class BrowserWrapper implements WrapsDriver {

    private static final ThreadLocal<BrowserWrapper> instance = new ThreadLocal<>();
    private WebDriver wrappedDriver;

    private BrowserWrapper() {
        try {
            wrappedDriver = DriverSingleton.getDriver();
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedDriver;
    }

    public static synchronized BrowserWrapper getInstance() {
        if (instance.get() == null) {
            instance.set(new BrowserWrapper());
            Log.debug("New browser is running now!");
        }
        return instance.get();
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                DriverSingleton.closeDriver();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.remove();
            Log.debug("Browser has stopped!");
        }
    }

    public void get(String url) {
        Log.info("Moving to URL: " + url);
        wrappedDriver.get(url);
    }

    public void moveTo(WebElement element) {
        Actions actions = new Actions(wrappedDriver);
        Log.info("Moving to: " + element.getTagName());
        actions.moveToElement(element).perform();
    }

    public WebElement findElement(By by) {
        Log.debug("Looking for element by: " + by);
        return wrappedDriver.findElement(by);
    }
}
