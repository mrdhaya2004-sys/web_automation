package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import wrappers.ConfigBrowser;
import wrappers.ConfigDriver;

public class Hooks {

    @Before
    public void launchBrowser() {

        ConfigDriver.getInstance().setDriver(
                ConfigBrowser.initBrowser("chrome", true));

        ConfigDriver.getInstance()
                .getDriver()
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        System.out.println("Browser launched successfully");
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) {

        if (ConfigDriver.getInstance().getDriver() != null) {

            final byte[] screenshot =
                    ((TakesScreenshot) ConfigDriver.getInstance().getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Step Screenshot");
        }
    }

    @After
    public void closeBrowser() {

        if (ConfigDriver.getInstance().getDriver() != null) {

            ConfigDriver.getInstance().getDriver().quit();
        }
    }
}
