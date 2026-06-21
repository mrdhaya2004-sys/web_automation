package facade;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.OrangeHRMPage;

public class OrangeHRMSteps {

    OrangeHRMPage page = new OrangeHRMPage();

    @Given("User launches OrangeHRM application")
    public void user_launches_orangehrm_application() {

        page.launchApplication();
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {

        page.enterUsername(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {

        page.enterPassword(password);
    }

    @And("User clicks Login button")
    public void user_clicks_login_button() {

        page.clickLoginButton();
    }

    @Then("User should navigate to Dashboard page")
    public void user_should_navigate_to_dashboard_page() {

        page.verifyDashboardPage();
    }

    @Then("Error message should be displayed")
    public void error_message_should_be_displayed() {

        page.verifyErrorMessage();
    }
}