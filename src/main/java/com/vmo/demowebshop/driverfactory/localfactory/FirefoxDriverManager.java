package com.vmo.demowebshop.driverfactory.localfactory;

import com.vmo.demowebshop.driverfactory.IBrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements IBrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();


        return new FirefoxDriver(options);
    }
}
