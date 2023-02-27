package uitest.m4;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class ClickingTest {

    @Test
    public void clickingTest() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        WebElement first = driver.findElement(By.id("firstName"));
        WebElement checkbox = driver.findElement(By.id("heard-about"));
        WebElement registerBtn = driver.findElement(By.id("register"));

        checkbox.click();
        registerBtn.click();
        DemoHelper.pause();

        driver.quit();
    }


    @Test
    public void moreClicking() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        WebElement checkbox = driver.findElement(By.id("heard-about"));
        Actions actions = new Actions(driver);

        actions.doubleClick(checkbox).perform();
        actions.contextClick(checkbox).perform();

        DemoHelper.pause();
        driver.quit();
    }
}
