package com.vmo.demowebshop.driverfactory.localfactory;

import org.openqa.selenium.WebDriver;

public class LocalFactory {
    private WebDriver driver;
    public WebDriver createDriver(String browser) {
        browser = browser.toUpperCase();

        switch (browser) {
            case "CHROME":
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case "H_CHROME":
                driver = new ChromeHeadlessDriverManager().getBrowserDriver();
                break;
            case "FIRE_FOX":
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case "EDGE":
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported: " + browser);
        }
        return driver;
    }
}
