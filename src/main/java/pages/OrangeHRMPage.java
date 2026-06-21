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
    System.out.println("Application already launched from Hooks");
}

    public void enterUsername(String username) {

        driver().findElement(txtUsername).clear();
        driver().findElement(txtUsername).sendKeys(username);
    }

    public void enterPassword(String password) {

        driver().findElement(txtPassword).clear();
        driver().findElement(txtPassword).sendKeys(password);
    }

    public void clickLoginButton() {

        driver().findElement(btnLogin).click();
    }

    public void verifyDashboardPage() {

        boolean status =
                driver().findElement(dashboard).isDisplayed();

        Assert.assertTrue(
                status,
                "Dashboard page is not displayed");
    }

    public void verifyErrorMessage() {

        boolean status =
                driver().findElement(errorMsg).isDisplayed();

        Assert.assertTrue(
                status,
                "Error message is not displayed");
    }
}
