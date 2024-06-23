package com.vmo.demowebshop.common;

import com.vmo.demowebshop.driverfactory.localfactory.LocalFactory;
import com.vmo.demowebshop.driverfactory.remotefactory.RemoteFactory;
import com.vmo.demowebshop.utils.AllureManager;
import com.vmo.demowebshop.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(TestListener.class)

public class BaseTest {
    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeSuite
    public void setUpSuite(@Optional("CHROME") String browser) {
        AllureManager.setAllureEnvironmentInformation("OS", System.getProperty("os.name"));
        AllureManager.setAllureEnvironmentInformation("Browser", browser);

    }

    @AfterSuite
    public void tearDownSuite() {
        AllureManager.writeAllureEnvironmentInformation();
        cleanAllBrowsers();
    }

    @BeforeMethod
    @Parameters({"browser", "modeRun"})
    public void setUp(@Optional("CHROME") String browser, @Optional("LOCAL") String modeRun) {
        BasePage.setSoftAssert();
        driver = getBrowserDriver(browser, modeRun);

        driver.get("https://demowebshop.tricentis.com/");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
        BasePage.assertAll();
    }


    public WebDriver getBrowserDriver(String browser, String modeRun) {
        switch (modeRun.toUpperCase()) {
            case "LOCAL":
                driver = new LocalFactory().createDriver(browser);

                break;
            case "REMOTE":
                driver = new RemoteFactory().createDriver(browser);
                break;
            default:
                throw new RuntimeException("The mode run is not supported: " + modeRun);
        }
        driver.manage().window().maximize();

        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void cleanAllBrowsers() {
        String osName = System.getProperty("os.name");
        String driverName = driver.toString().toLowerCase();
        try {
            String cmd;
            if (driverName.contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                    executeCommand(cmd);
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /IM chrome.exe /T";
                    executeCommand(cmd);
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                    executeCommand(cmd);
                }
            }
            if (driverName.contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                    executeCommand(cmd);
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /IM firefox.exe /T";
                    executeCommand(cmd);
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                    executeCommand(cmd);
                }
            } else if (driverName.contains("safari")) {
                cmd = "pkill safaridriver";
                executeCommand(cmd);
            }
        } catch (Exception e) {
            System.err.println("e = " + e);
        }

    }

    private static void executeCommand(String cmd) throws InterruptedException, IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
    }
}
