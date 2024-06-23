package com.vmo.demowebshop.driverfactory;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IBrowserFactory {
    public WebDriver getBrowserDriver() throws MalformedURLException;
}
