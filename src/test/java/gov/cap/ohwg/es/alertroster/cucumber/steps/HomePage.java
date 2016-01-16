package gov.cap.ohwg.es.alertroster.cucumber.steps;

import gov.cap.ohwg.es.alertroster.cucumber.util.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ckovacs on 1/16/16.
 */
public class HomePage {

    private WebDriver browser = new Browser();

    public boolean isBeingViewed() {
        return "Home".equals(browser.getTitle());
    }

    public String getGroupName() {
        return browser.findElement(By.id("grouptitle")).getText();
    }
}
