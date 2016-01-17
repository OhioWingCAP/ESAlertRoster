package gov.cap.ohwg.es.alertroster.cucumber.util;

import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by ckovacs on 11/9/15.
 */

public class Browser extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            //quitGlobalInstance();
        }
    };

    private static void quitGlobalInstance() {
        WebDriver driver = REAL_DRIVER;
        REAL_DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver getRealDriver() {
        if (REAL_DRIVER == null) {
            String browser = System.getProperty("BROWSER");
            if (browser == null) {
                browser = "chrome";
            }
            switch (browser) {
                case "chrome":
                    REAL_DRIVER = new ChromeDriver();
                    break;
                default:
                    REAL_DRIVER = new PhantomJSDriver();
            }
            REAL_DRIVER.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            REAL_DRIVER.manage().window().setSize(new Dimension(1280, 1024));
            REAL_DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        }
        return REAL_DRIVER;
    }

    public Browser() {
        super(getRealDriver());
    }


    public static WebElement waitFor(final By by) {
        return waitFor(REAL_DRIVER, by);
    }

    public static WebElement waitFor(WebDriver driver, final By by) {
        return waitFor(driver, by, 10);
    }

    public static WebElement waitFor(WebDriver driver, final By by, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(by);
                return element != null;
            }
        });
        return driver.findElement(by);
    }

    public static void fillOut(String id, String value) {
        if (StringUtil.isBlank(id) || StringUtil.isBlank(value)) {
            return;
        }
        waitFor(REAL_DRIVER, By.id(id)).sendKeys(value);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        try {
            super.quit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
