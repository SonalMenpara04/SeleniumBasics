package com.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static RemoteWebDriver driver;

    public static RemoteWebDriver OpenBrowser(String browserName) {
        String intern = browserName.intern();
        if (intern.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("headless");
            DesiredCapabilities dc=new DesiredCapabilities();
            dc.setCapability(browserName,"CHROME");
            dc.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
            driver = new ChromeDriver(chromeOptions);
            System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        } else if (intern.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "browser_exes/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (intern.equals("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            //edgeOptions.merge("start-maximized");
            DesiredCapabilities dc2=new DesiredCapabilities();
            dc2.setCapability(browserName,"EDGE");
            dc2.setCapability(ChromeOptions.CAPABILITY,dc2);
            dc2.setCapability(CapabilityType.ACCEPT_SSL_CERTS,false);
            driver = new EdgeDriver(edgeOptions);
            System.setProperty("webdriver.edge.driver", "browser_exes/msedgedriver.exe");
        } else if (intern.equals("default")) {
            driver = null;
        } else {
            throw new IllegalStateException("Unexpected value: " + browserName.intern());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        return driver;
    }
}
