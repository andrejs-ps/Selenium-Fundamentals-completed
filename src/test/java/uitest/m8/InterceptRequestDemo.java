package uitest.m8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;
import org.openqa.selenium.devtools.v135.network.model.Request;
import org.openqa.selenium.devtools.v135.network.model.Response;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class InterceptRequestDemo {

// Firefox Driver .getDevTools() method and overall support for CDP deprecated and removed since Selenium v4.27
// https://www.selenium.dev/blog/2025/remove-cdp-firefox/

    WebDriver driver;
    DevTools devTools;

    @Test
    public void captureRequestTraffic() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(),
                requestEvent -> {
                    Request r = requestEvent.getRequest();
                    System.out.printf("URL: %s, Method: %s \n", r.getUrl(), r.getMethod());
                }
        );
        driver.get("http://127.0.0.1:8000/index.html");

    }

    @Test
    public void captureResponseTraffic() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(), Optional.empty(), Optional.empty()));

        List<Integer> statuses = new ArrayList<>();
        devTools.addListener(Network.responseReceived(),
                responseReceived -> {
                    Response r = responseReceived.getResponse();
                    System.out.printf("Response status: %s \n", r.getStatus());
//                    Assert.assertTrue(r.getStatus() <= 400);
//                    Assert.assertFalse(r.getStatus() <= 400);
                    statuses.add(r.getStatus());
                }
                );

        driver.get("http://127.0.0.1:8000/index.html");
        statuses.forEach(status -> Assert.assertFalse(status <= 400));
    }


    @Test
    public void manipulateTraffic() {

        driver = new ChromeDriver();
        devTools = getDevTool(driver);
//        devTools.send(Network.setBlockedURLs(List.of("*/footer.js")));


        driver.get("http://127.0.0.1:8000/index.html");

        WebElement location = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(visibilityOfElementLocated(By.id("location")));
        Assert.assertTrue(location.getText().contains("You are visiting us from"));
    }

    private static DevTools getDevTool(WebDriver driver) {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));
        return devTools;
    }

    @AfterMethod
    public void cleanup() {
        devTools.send(Network.disable());
        devTools.close();
        driver.quit();
    }
}
