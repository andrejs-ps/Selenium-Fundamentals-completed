package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static helper.Pages.HOME;
import static helper.Pages.LOANS;

public class ExecuteJavaScriptDemo {

    WebDriver driver;

    @Test(description = "Test will fail in its current state")
    public void firstAttempt() {
        driver = DriverFactory.newDriver();
        driver.get(LOANS);

        driver.findElement(By.id(("apply"))).click();

        var button = driver.findElement(By.id(("apply")));
        DemoHelper.pause();
        button.click();
    }

    @Test
    public void customJavaScriptScrollTest() {
        driver = DriverFactory.newDriver();
        driver.get(LOANS);
        WebElement button = driver.findElement(By.id(("apply")));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("console.log('hi')");

        js.executeScript("arguments[0].scrollIntoView();", button);
        DemoHelper.pause();
        button.click();
    }

    @Test
    public void customJavaScriptClickTest() {
        driver = DriverFactory.newDriver();
        driver.get(LOANS);

        WebElement button = driver.findElement(By.id(("apply")));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", button);

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertTrue(message.isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        driver.quit();
    }
}
