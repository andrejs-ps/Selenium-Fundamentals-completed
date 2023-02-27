package uitest.m5;

import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class RelativeLocatorsTest {

    WebDriver driver;

    @Test
    public void relativeLocator() {
        driver = DriverFactory.newDriver();
        driver.get(HOME);

        WebElement email = driver.findElement(By.id("email"));
        RelativeLocator.RelativeBy input = RelativeLocator.with(By.tagName("input"));

        WebElement datePicker = driver.findElement(input.toRightOf(email));
        System.out.println(datePicker.getAttribute("type"));    // date

        WebElement firstName = driver.findElement(input.above(email));
        System.out.println(firstName.getAttribute("id"));       // firstName

    }

    @AfterMethod
    void cleanUp() {
        driver.quit();
    }
}