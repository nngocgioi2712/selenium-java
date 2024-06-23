package com.vmo.demowebshop.driverfactory.remotefactory;

import com.vmo.demowebshop.driverfactory.IBrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteEdgeDriverManager implements IBrowserFactory {

    @Override
    public WebDriver getBrowserDriver() throws MalformedURLException {
        EdgeOptions options = new EdgeOptions();

        return new RemoteWebDriver(new URL("http://localhost:4444"), options);
    }
}
