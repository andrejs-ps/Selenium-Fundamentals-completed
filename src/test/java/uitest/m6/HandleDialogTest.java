package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class HandleDialogTest {

    @Test
    void dismissAlertTest() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        var first = driver.findElement(By.id("firstName"));
        var last = driver.findElement(By.id("lastName"));

        first.sendKeys("John");
        last.sendKeys("Smith");

        DemoHelper.pause();
        driver.findElement(By.id("clear")).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        Assert.assertEquals(first.getAttribute("value"), "John");
        Assert.assertEquals(last.getAttribute("value"), "Smith");

        driver.quit();
    }


    @Test
    void acceptAlertTest() {

        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        var first = driver.findElement(By.id("firstName"));
        var last = driver.findElement(By.id("lastName"));

        first.sendKeys("John");
        last.sendKeys("Smith");

        DemoHelper.pause();
        driver.findElement(By.id("clear")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assert.assertEquals(first.getAttribute("value"), "");
        Assert.assertEquals(last.getAttribute("value"), "");

        driver.quit();
    }
}
