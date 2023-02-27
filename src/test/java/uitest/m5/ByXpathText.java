package uitest.m5;

import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static helper.Pages.HOME;
import static helper.Pages.SAVINGS;

public class ByXpathText {

    WebDriver driver;

    @Test
    public void byXpath_1() {
        driver = DriverFactory.newDriver();
        driver.get(SAVINGS);

        var cell_1 = driver.findElement(By.xpath("/html/body/main/div/div/div/form/div/div[4]/table/tbody/tr[1]/td[4]"));
        System.out.println("Chrome full xpath: " + cell_1.getText());

        var cell_2 = driver.findElement(By.xpath("//*[@id=\"rates\"]/tbody/tr[1]/td[4]"));
        System.out.println("Chrome xpath: " + cell_2.getText());

    }

    @Test
    public void byXpath_2() {
        driver = DriverFactory.newDriver();
        driver.get(HOME);

        var button = driver.findElement(By.xpath("//form/button[contains(text(), 'Register')]"));
        System.out.println(button.getText());
    }

    @AfterMethod
    void cleanUp() {
        driver.quit();
    }
}
