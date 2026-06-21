package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import wrappers.GenericWrappersWeb;

public class OrangeHRMPage extends GenericWrappersWeb {

    By txtUsername = By.name("username");

    By txtPassword = By.name("password");

    By btnLogin = By.xpath("//button[@type='submit']");

    By dashboard =
            By.xpath("//h6[text()='Dashboard']");

    By errorMsg =
            By.xpath("//p[contains(@class,'alert-content-text')]");

    public void launchApplication() {

        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    public void enterUsername(String username) {

        driver.findElement(txtUsername).sendKeys(username);
    }

    public void enterPassword(String password) {

        driver.findElement(txtPassword).sendKeys(password);
    }

   public void clickLoginButton() {

    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(30));

    WebElement loginBtn =
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xpaths.getProperty("btn-login-submit"))));

    loginBtn.click();
}

    public void verifyDashboardPage() {

        boolean status =
                driver.findElement(dashboard).isDisplayed();

        Assert.assertTrue(
                status,
                "Dashboard page is not displayed");
    }

    public void verifyErrorMessage() {

        boolean status =
                driver.findElement(errorMsg).isDisplayed();

        Assert.assertTrue(
                status,
                "Error message is not displayed");
    }
}
