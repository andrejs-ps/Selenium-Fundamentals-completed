package uitest.m9.pom;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Loans;
import pages.LoansVersion2;

import static factory.DriverFactory.newChromeDriver;
import static pages.LoansVersion2.Period.*;

public class ChainedMethodsDemo {

    WebDriver driver;

    @BeforeMethod
    public void initDriver() {
        driver = newChromeDriver();
    }

    @Test
    public void loansTest() {
        var page = LoansVersion2.loansPage(driver);

        String result = page.goTo()
                .enterBorrowAmount("500")
                .selectTimePeriod(ONE_MONTH)
                .getResultMessageText();

        Assert.assertEquals(result, "You will pays us back 1000");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
