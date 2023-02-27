package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoansVersion2 {

    private static final String URL = "file:///" + System.getProperty("user.dir") + "\\src\\web\\loans.html";
    private final WebDriver driver;

    private LoansVersion2(WebDriver driver) {
        this.driver = driver;
    }

    public static LoansVersion2 loansPage(WebDriver driver) {
        return new LoansVersion2(driver);
    }

    public LoansVersion2 goTo() {
        driver.get(URL);
        return this;
    }

    public LoansVersion2 enterBorrowAmount(String value) {
        driver.findElement(By.id("borrow")).sendKeys(value);
        return this;
    }

    public LoansVersion2 selectTimePeriod(LoansVersion2.Period period) {
        Select dropdown = new Select(driver.findElement(By.id("period")));
        dropdown.selectByVisibleText(period.toString());
        return this;
    }

    public String getResultMessageText() {
        var result = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(visibilityOfElementLocated(By.id("result")));
        return result.getText();
    }

    public enum Period {
        ONE_MONTH("1 month"),
        TWO_MONTHS("2 months"),
        ONE_YEAR("1 year");


        final String period;
        Period(String period) {
            this.period = period;
        }
        @Override
        public String toString() {
            return period;
        }
    }

}
