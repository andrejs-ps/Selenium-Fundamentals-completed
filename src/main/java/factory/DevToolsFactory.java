package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;

import java.util.Optional;

public class DevToolsFactory {

    public static DevTools newChromeDevTool(WebDriver driver) {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));
        return devTools;
    }
}
