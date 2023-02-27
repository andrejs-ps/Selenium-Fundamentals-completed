package uitest.m10;

import helper.DemoHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ServerTestDemo {

    @Test
    public void test() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.setCapability("platformName", "Windows 11");
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.get("https://www.pluralsight.com/");
        DemoHelper.pause();
        DemoHelper.pause();
        driver.quit();
    }

    @Test
    public void test2() throws MalformedURLException {

        EdgeOptions options = new EdgeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.get("https://www.pluralsight.com/");
        DemoHelper.pause();
        DemoHelper.pause();
        driver.quit();
    }


}
