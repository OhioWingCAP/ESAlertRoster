package gov.cap.ohwg.es.alertroster.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.cap.ohwg.es.alertroster.cucumber.pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Created by ckovacs on 1/16/16.
 */
public class LoginSteps {

    private final LoginPage loginPage;
    private final HomePage homePage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();
    }

    @Given("^I am viewing the login page$")
    public void i_am_viewing_the_login_page() throws Throwable {
        loginPage.visit();
    }

    @When("^I enter username \"([^\"]*)\"$")
    public void i_enter_username(String username) throws Throwable {
        loginPage.setUsername(username);
    }

    @When("^I enter password \"([^\"]*)\"$")
    public void i_enter_password(String password) throws Throwable {
        loginPage.setPassword(password);
    }

    @Then("^I am viewing the home page$")
    public void i_am_viewing_the_home_page() throws Throwable {
        assertTrue(homePage.isBeingViewed());
    }

    @And("^I click login$")
    public void iClickSubmit() throws Throwable {
        loginPage.clickLogin();
    }

    @Given("^I am logged in as a \"([^\"]*)\"$")
    public void iAmLoggedInAsA(String user) throws Throwable {
        loginPage.logout();
        i_enter_username("test.user@ohwg.cap.gov");
        i_enter_password("THrQ;3E8f#4vcKF");
        iClickSubmit();
        i_am_viewing_the_home_page();
    }

    @Then("^I see the units in \"([^\"]*)\"$")
    public void iSeeTheUnitsIn(String groupName) throws Throwable {
        assertThat(homePage.getGroupName(), is(groupName));
    }
}
