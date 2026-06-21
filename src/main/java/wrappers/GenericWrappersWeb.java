package wrappers;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;

public class GenericWrappersWeb extends AbstractTestNGCucumberTests {

    public static final long WAIT = 5;
    public static String url, browser;

    /**
     * FIX: 'protected' la irundhadhai 'public' nu mathiyacha.
     * Ippo unga project la iruka endha package (pages, facade, hooks) la irundhum 
     * indha driver-ai direct-ah access panna mudiyum. 'is not visible' error poidum.
     */
    public WebDriver driver;

    @BeforeTest
    public void launchApp() {
        try {
            url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
            browser = "chrome";
            launchWebApp(url, browser);
            System.out.println("Web Execution " + browser + " Started:::");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchWebApp(String url, String browser) {
        WebDriver rawDriver = ConfigBrowser.initBrowser(browser, true);
        ConfigDriver.getInstance().setDriver(rawDriver);
        ConfigDriver.getInstance().getDriver().navigate().to(url);
        System.out.println("URL Launched: " + url);
    }

    /**
     * Active thread oda fresh WebDriver instance-ai return seiyum helper.
     */
    public WebDriver driver() {
        this.driver = ConfigDriver.getInstance().getDriver();
        return this.driver;
    }

    public void clickElement(WebElement ele) {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ele.click();
    }

    public void typeOnElement(WebElement ele, String data) {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ele.clear();
        ele.sendKeys(data);
    }

    public void clearOnElement(WebElement ele) {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ele.clear();
    }

    public void slowSendKeys(String keysToSend, WebElement element) {
        for (char c : keysToSend.toCharArray()) {
            element.sendKeys(Character.toString(c));
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean eleIsDisplayed(WebElement ele) {
        return ele.isDisplayed();
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public void verifyText(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    public WebElement getWebElementDynamicPath(String xpath, String newValue) {
        xpath = xpath.replace("com.msf2", newValue.trim());
        return driver().findElement(By.xpath(xpath));
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) return false;
        try {
            Double.parseDouble(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void non_visible_click(WebElement ele) {
        new WebDriverWait(driver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(ele));
        if (ele.isDisplayed()) {
            ele.click();
        }
    }

    public void pageRefresh() {
        driver().navigate().refresh();
    }

    public static void zoomout(int zoom) {
        WebDriver currentDriver = ConfigDriver.getInstance().getDriver();
        if (currentDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) currentDriver).executeScript("document.body.style.zoom ='" + zoom + "%'");
        }
    }

    // Image comparison hooks
    public boolean compareImagePixels(Image img1, Image img2) {
        try {
            PixelGrabber grab1 = new PixelGrabber(img1, 0, 0, -1, -1, false);
            PixelGrabber grab2 = new PixelGrabber(img2, 0, 0, -1, -1, false);
            if (grab1.grabPixels() && grab2.grabPixels()) {
                int[] data1 = (int[]) grab1.getPixels();
                int[] data2 = (int[]) grab2.getPixels();
                return java.util.Arrays.equals(data1, data2);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

    @AfterTest
    public void quitApp() {
        ConfigDriver.getInstance().closeBrowser();
        System.out.println("Closed Browser Context Thread Safe");
    }
}