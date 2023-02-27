package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Savings {

    private static final String URL = "file:///" + System.getProperty("user.dir") + "\\src\\web\\savings.html";
    private final WebDriver driver;

    private Savings(WebDriver driver) {
        this.driver = driver;
    }

    public static Savings savingsPage(WebDriver driver) {
        return new Savings(driver);
    }

    public void goTo() {
        driver.get(URL);
    }

    public void enterDeposit(String value) {
        driver.findElement(By.id("deposit")).sendKeys(value);
    }

    public void selectTimePeriod(Period period) {
        Select dropdown = new Select(driver.findElement(By.id("period")));
        dropdown.selectByVisibleText(period.toString());
    }

    public WebElement resultMessage() {
        return driver.findElement(By.id("result"));
    }


    public enum Period {
        SIX_MONTHS("6 months"),
        ONE_YEAR("1 Year"),
        TWO_YEARS("2 Years");


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
