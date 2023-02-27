package uitest.m9.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home;

import java.util.List;

import static factory.DriverFactory.newChromeDriver;

public class PomAbstractionLevelOneTest {

    WebDriver driver;

    @BeforeMethod
    public void initDriver() {
        driver = newChromeDriver();
    }

    @Test
    public void homePageTest() {

        var home = Home.homePage(driver);

        home.goTo();
        home.firstName().sendKeys("John");
        home.lastName().sendKeys("");
        home.registerButton().click();

        List<WebElement> feedbackList = home.invalidFeedback();
        Assert.assertFalse(feedbackList.get(0).isDisplayed());
        Assert.assertEquals(feedbackList.get(1).getText(), "Valid last name is required");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
