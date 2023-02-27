package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Map;

public class DriverFactory {

    public static WebDriver newChromeDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static WebDriver newEdgeDriver() {
        return applyCommonSetup(new EdgeDriver());
    }

    public static WebDriver newFirefoxDriver() {
        return applyCommonSetup(new FirefoxDriver());
    }

    public static WebDriver newDevice(String deviceName) {
        Map<String, String> mobileEmulation = Map.of("deviceName", deviceName);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        return new ChromeDriver(options);
    }

    private static WebDriver applyCommonSetup(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
