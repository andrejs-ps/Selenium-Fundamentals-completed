package uitest.m4;

import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static helper.Pages.HOME;

public class FindMultipleElementsTest {

    @Test
    public void multipleElementsTest() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        List<WebElement> feedback = driver.findElements(By.className("invalid-feedback"));

        print(feedback.get(0).getText());
        print(feedback.get(1).getText());

        driver.findElement(By.id("register")).click();
        print(feedback.get(0).getText());
        print(feedback.get(1).getText());

        driver.quit();
    }

    private void print(String text) {
        System.out.println("Feedback:" + text);
    }

}
