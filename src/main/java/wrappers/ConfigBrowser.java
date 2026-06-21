package wrappers;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfigBrowser {

    public static WebDriver initBrowser(String browser, boolean privateMode) {
        WebDriver driver = null;
        switch (browser.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDefault(privateMode);
                break;
            case "firefox":
                driver = initFireFox();
                break;
            case "edge":
                driver = initEdge();
                break;
            default:
                driver = initChromeDefault(privateMode);
                break;
        }
        return driver;
    }

    static WebDriver initChromeDefault(boolean privateMode) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--force-device-scale-factor=0.7");
        options.addArguments("--disable-infobars");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-browser-side-navigation");
        
        if (privateMode) {
            options.addArguments("--incognito");
        }

        WebDriver driver = new ChromeDriver(options);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='80%'");
        
        driver.manage().window().maximize();
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        
        return driver;
    }

    static WebDriver initEdge() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.transform = 'scale(0.7)';");
        js.executeScript("document.body.style.transformOrigin = '0 0';");

        return driver;
    }

    static WebDriver initFireFox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
