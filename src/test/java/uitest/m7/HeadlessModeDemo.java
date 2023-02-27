package uitest.m7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class HeadlessModeDemo {

    @Test
    public void headlessDemo() {

        ChromeOptions options = new ChromeOptions();
        // deprecated
//        options.setHeadless(true);
        options.addArguments("headless=true");

        WebDriver driver = new ChromeDriver(options);
        driver.get(HOME);

        WebElement button = driver.findElement(By.id("register"));
        System.out.println(button.getText()); // proves that we loaded the page

        driver.quit();
    }
}
