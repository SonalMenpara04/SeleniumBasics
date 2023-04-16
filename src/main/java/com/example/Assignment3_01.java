package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class Assignment3_01 {

    static WebDriver driver;

    @Test
    public void framesExample() {
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_myfirst");
        driver.manage().window().maximize();

        driver.switchTo().frame("iframeResult");

        WebElement btnDisplayDateTime=new WebDriverWait(driver, 10)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Click me to display Date and Time.']")));
        btnDisplayDateTime.click();

        WebElement txtDisplayDateTime=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("demo")));

        String expectedText=txtDisplayDateTime.getText();

        Date objDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzzz)");
        String actualText=dateFormat.format(objDate);

        Assert.assertEquals(actualText,expectedText);
        driver.switchTo().defaultContent();

        WebElement btnRun=new WebDriverWait(driver, 2000)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("runbtn")));
        btnRun.click();

        driver.switchTo().frame("iframeResult");
        WebElement lblDisplayDateTime=driver.findElement(By.id("demo"));
        Assert.assertTrue(!lblDisplayDateTime.isDisplayed(),"Date text field present which is not expected");
    }

    @Test
    public void framesHeadlessExample() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("headless");
        DesiredCapabilities dc=new DesiredCapabilities();
        dc.setCapability(CapabilityType.BROWSER_NAME,"CHROME");
        dc.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_myfirst");

        driver.switchTo().frame("iframeResult");

        WebElement btnDisplayDateTime=new WebDriverWait(driver, 10)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Click me to display Date and Time.']")));
        btnDisplayDateTime.click();

        WebElement txtDisplayDateTime=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("demo")));

        String expectedText=txtDisplayDateTime.getText();

        Date objDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzzz)");
        String actualText=dateFormat.format(objDate);

        Assert.assertEquals(actualText,expectedText);
        driver.switchTo().defaultContent();

        WebElement btnRun=new WebDriverWait(driver, 2000)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("runbtn")));
        btnRun.click();
        driver.switchTo().frame("iframeResult");
        WebElement lblDisplayDateTime=driver.findElement(By.id("demo"));
        Assert.assertTrue(!lblDisplayDateTime.isDisplayed(),"Date text field present which is not expected");
    }
}
