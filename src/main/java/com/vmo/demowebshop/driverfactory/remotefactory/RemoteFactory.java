package com.vmo.demowebshop.driverfactory.remotefactory;

import org.openqa.selenium.WebDriver;

public class RemoteFactory {
    private WebDriver driver;
    public WebDriver createDriver(String browser) {
        browser = browser.toUpperCase();

        try {
            switch (browser) {
                case "CHROME":
                    driver = new RemoteChromeDriverManager().getBrowserDriver();
                    break;
                case "H_CHROME":
                    driver = new RemoteChromeHeadlessDriverManager().getBrowserDriver();
                    break;
                case "FIRE_FOX":
                    driver = new RemoteFirefoxDriverManager().getBrowserDriver();
                    break;
                case "EDGE":
                    driver = new RemoteEdgeDriverManager().getBrowserDriver();
                    break;
                default:
                    throw new RuntimeException("Browser is not supported: " + browser);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return driver;
    }
}
