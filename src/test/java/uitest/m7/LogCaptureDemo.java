package uitest.m7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.logging.Level;

import static helper.Pages.HOME;

public class LogCaptureDemo {

    WebDriver driver;

    @Test
    public void logCaptureDemo() {

        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(ChromeOptions.LOGGING_PREFS, logs);
        driver = new ChromeDriver(options);
        driver.get(HOME);
        driver.findElement(By.id("register")).click();

        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        Assert.assertFalse(browserLogs.getAll().isEmpty());

        browserLogs.forEach(System.out::println);

        browserLogs.forEach(logEntry -> System.out.println(logEntry.getLevel() + " " + logEntry.getMessage()));

        // this fails the test as intended - the app logs an error
        browserLogs.forEach(logEntry -> checkNoError(logEntry));
    }

    private void checkNoError(LogEntry logEntry) {
        Assert.assertNotEquals(logEntry.getLevel().getName(), "SEVERE");
    }


    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}
