package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static helper.Pages.HOME;
import static helper.Pages.SAVINGS;

public class StorageAndCookiesTest {

    @Test
    public void storageTest()  {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        var first = driver.findElement(By.id("firstName"));
        var last = driver.findElement(By.id("lastName"));
        var save = driver.findElement(By.id("save"));

        first.sendKeys("Maria");
        last.sendKeys("Diaz");
        save.click();

        WebStorage webStorage = (WebStorage) driver;
        SessionStorage storage = webStorage.getSessionStorage();
        storage.keySet()
                        .forEach(key -> System.out.println(key + "=" + storage.getItem(key)));
        DemoHelper.pause();
        driver.get(SAVINGS);
        driver.navigate().back();

        DemoHelper.pause();

        var first_1 = driver.findElement(By.id("firstName"));
        var last_1 = driver.findElement(By.id("lastName"));
        Assert.assertEquals(first_1.getAttribute("value"), "Maria");
        Assert.assertEquals(last_1.getAttribute("value"), "Diaz");


        storage.clear();
        driver.navigate().refresh();

        DemoHelper.pause();
        var first_2 = driver.findElement(By.id("firstName"));
        var last_2 = driver.findElement(By.id("lastName"));
        Assert.assertEquals(first_2.getAttribute("value"), "");
        Assert.assertEquals(last_2.getAttribute("value"), "");

        driver.quit();
    }

    @Test
    public void cookiesTest()  {
        WebDriver driver = DriverFactory.newDriver();
        WebDriver.Options options = driver.manage();

        Set<Cookie> cookies = options.getCookies();
        Cookie thing = options.getCookieNamed("thing");
        options.deleteAllCookies();
        // etc.

    }
}
