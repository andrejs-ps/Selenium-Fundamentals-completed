package uitest.m6;

import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import wait.WaitingUtils;

import java.time.Duration;

import static helper.Pages.LOANS;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitingTest {

    WebDriver driver;

    @Test
    public void implicitWaitTest() {

        driver = new ChromeDriver();
        driver.get(LOANS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.id("borrow")).sendKeys("500");

//        Assert.assertTrue(driver.findElement(By.id("result")).isDisplayed());
        driver.findElement(By.id("result")).click();
    }


    @Test
    public void explicitWaitTest() {
        driver = new ChromeDriver();
        driver.get(LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement result = wait.until(visibilityOfElementLocated(By.id("result")));
        Assert.assertTrue(result.isDisplayed());
        result.click(); // interactable?

    }


    @Test
    public void explicitWaitTestRefactored() {
        driver = new ChromeDriver();
        driver.get(LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        WebElement result = WaitingUtils.waitUntilClickable(driver, By.id("result"));
        Assert.assertTrue(result.isDisplayed());
    }


    @Test
    public void fluentWaitTest() {
        driver = DriverFactory.newDriver();
        driver.get(LOANS);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        driver.findElement(By.id("borrow")).sendKeys("500");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        element.click();
    }


    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}
