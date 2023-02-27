package uitest.m9;

import factory.DevToolsFactory;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.testng.Assert;
import org.testng.annotations.Test;
import wait.WaitingUtils;

import java.time.Duration;

import static factory.DriverFactory.*;
import static helper.Pages.LOANS;
import static org.testng.Assert.assertEquals;
import static wait.WaitingUtils.*;

public class BasicRefactoringDemo {

    @Test
    public void beforeRefactoring() {
        // setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless=true");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

        // do things
    }

    @Test
    public void newDriverAndToolsDemo() {

        WebDriver chromeDriver = newChromeDriver();

        WebDriver edgeDriver = newEdgeDriver();

        WebDriver nexus = newDevice("Nexus 5");

        DevTools tools = DevToolsFactory.newChromeDevTool(chromeDriver);

    }

    @Test
    public void refactoredTestDemo() {

        var driver = newChromeDriver();
        driver.get(LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        var message = WaitingUtils.waitUntilVisible(driver, By.id("result"), 6);
        assertEquals(message.getText(), "You will pays us back 1000");
        driver.quit();

    }
}
