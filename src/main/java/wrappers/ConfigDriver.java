package wrappers;

import org.openqa.selenium.WebDriver;

public class ConfigDriver {

    private ConfigDriver() {}

    private static final ConfigDriver driverInstance = new ConfigDriver();

    public static ConfigDriver getInstance() {
        return driverInstance;
    }

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return this.driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public void closeBrowser() {
        if (this.driver.get() != null) {
            this.driver.get().quit();
            this.driver.remove();
        }
    }
}