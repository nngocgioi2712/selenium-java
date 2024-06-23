package com.vmo.demowebshop.driverfactory.remotefactory;

import com.vmo.demowebshop.driverfactory.IBrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class RemoteChromeDriverManager implements IBrowserFactory {

    @Override
    public WebDriver getBrowserDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--incognito");

        return new RemoteWebDriver(new URL("http://localhost:4444"), options);
    }
}
