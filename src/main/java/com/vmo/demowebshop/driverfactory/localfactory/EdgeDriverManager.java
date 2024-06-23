package com.vmo.demowebshop.driverfactory.localfactory;

import com.vmo.demowebshop.driverfactory.IBrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements IBrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        EdgeOptions options = new EdgeOptions();

        return new EdgeDriver(options);
    }
}
