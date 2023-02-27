package uitest.m4;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class TypingTest {

    @Test
    public void typingTest() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        WebElement first = driver.findElement(By.id("firstName"));
        WebElement last = driver.findElement(By.id("lastName"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement dob = driver.findElement(By.id("dob"));

        first.sendKeys("Sofia");
        last.sendKeys("Diaz");
        email.sendKeys("sofia@email.com");
        DemoHelper.pause();
        email.clear();
        email.sendKeys("sofia@email.com");
        dob.sendKeys("22/03/2022");

        DemoHelper.pause();
        driver.quit();
    }
}
