package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class TestRunner2 {

    WebDriver driver;
    @Test
    public void switchBetweenWindows(){
        //RemoteWebDriver driver= (RemoteWebDriver) BrowserFactory.OpenBrowser("chrome");
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/documentation/");
        System.out.println(driver.getTitle());

        WebElement editThisPageLink=new WebDriverWait(driver, 2000).pollingEvery(Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=' Edit this page']")));
        editThisPageLink.click();

        System.out.println("title : " + driver.getTitle());

        Set<String> allWindows=driver.getWindowHandles();
        ArrayList<String> windows= new ArrayList<>(allWindows);

        driver.switchTo().window(windows.get(1));
        System.out.println(driver.getTitle());

        driver.switchTo().window(windows.get(0));
        System.out.println(driver.getTitle());
    }

    @Test
    public void framesAndAlerts() throws InterruptedException {
        //RemoteWebDriver driver= (RemoteWebDriver) BrowserFactory.OpenBrowser("chrome");
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");

        //driver.switchTo().frame(1);
        //driver.switchTo().frame("iframeResult");
        //WebElement frameElement=driver.findElement(By.id("iframeResult"));
        //driver.switchTo().frame(frameElement);

        FluentWait<WebDriver> fluentWait=new WebDriverWait(driver,200);
        fluentWait.pollingEvery(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));
       //fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
       //fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")));

        WebElement tryButton=driver.findElement(By.xpath("//button[text()='Try it']"));
        tryButton.click();

        Thread.sleep(1000);
        System.out.println(driver.getTitle());
    }

    @Test
    public void fileUploadDownload() throws AWTException {
        //RemoteWebDriver driver= (RemoteWebDriver) BrowserFactory.OpenBrowser("chrome");
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.naukri.com/registration/createAccount?othersrcp=22636");

        WebElement uploadButton=new WebDriverWait(driver, 2000)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Upload Resume']")));

        uploadButton.click();

        setClipboardData("E:\\Resume\\SonalMenparaResume2022.docx");

        Robot rb = new Robot();

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

    }

    public static void setClipboardData(String pathOfFile){
        StringSelection stringselection=new StringSelection(pathOfFile);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
    }

    @Test
    public void openSameBrowser() throws AWTException, InterruptedException {
        ChromeOptions co=new ChromeOptions();
        co.addArguments("user-data-dir=C:\\Users\\Sonal\\AppData\\Local\\Google\\chrome\\User Data\\Default");
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(co);

        driver.get("https://www.selenium.dev/downloads/");
        WebElement download_4_8_1=driver.findElementByXPath("//a[text()='4.8.1']");
        download_4_8_1.click();

        Robot robot = new Robot();
        for (int i = 0; i < 78; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("completed clicking on downloads button");
    }

    @Test
    public void downloadInParticularLocation() throws AWTException, InterruptedException {

        EdgeOptions eo = new EdgeOptions();
        //eo.addArguments("user-data-dir=C:\\Users\\Sonal\\AppData\\Local\\Google\\chrome\\User Data\\Default");

        System.setProperty("webdriver.edge.driver", "browser_exes/msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver(eo);

        driver.get("edge://settings/downloads");
        WebElement changeButton = driver.findElement(By.xpath("//span[normalize-space()='Change']"));
        changeButton.click();
        Thread.sleep(2000);
        setClipboardData("C:\\Users\\Sonal\\Downloads\\document.pdf");

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.get("https://www.selenium.dev/downloads/");
        WebElement downLoad_4_8_1 = driver.findElement(By.xpath("//a[normalize-space()='4.8.1']"));
        downLoad_4_8_1.click();
        System.out.println("completed clicking on downloads button");
    }
}
