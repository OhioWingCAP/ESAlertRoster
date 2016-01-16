package gov.cap.ohwg.es.alertroster.cucumber.pages;

import gov.cap.ohwg.es.alertroster.cucumber.util.App;
import gov.cap.ohwg.es.alertroster.cucumber.util.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ckovacs on 1/16/16.
 */
public class LoginPage {
    private WebDriver browser = new Browser();
    private App app = new App();

    public LoginPage() {

    }

    public void visit() {
        browser.navigate().to(app.getUrlFor("login"));
    }

    public void setUsername(String username) {
        browser.findElement(By.name("username")).sendKeys(username);
    }

    public void setPassword(String password) {
        browser.findElement(By.name("password")).sendKeys(password);
    }

    public void clickLogin() {
        browser.findElement(By.name("submit")).click();
    }

    public void logout() {
        browser.navigate().to(app.getUrlFor("logout"));
    }
}
