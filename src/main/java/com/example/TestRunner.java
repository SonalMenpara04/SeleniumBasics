package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class TestRunner {

    static RemoteWebDriver driver;
    @Test
    public void testCase1() throws IOException, InterruptedException {
        driver= BrowserFactory.OpenBrowser("chrome");
        driver.get("https://www.facebook.com");

        By by_linkText= By.xpath("//a[text()='Create new account']");
        WebElement element=driver.findElement(by_linkText);
        element.click();

        Thread.sleep(1000);

        WebElement txtFirstName=driver.findElement(By.xpath("//input[@name='firstname']"));
        txtFirstName.sendKeys("Sonal");

        WebElement txtSurName=driver.findElement(By.xpath("//input[@name='lastname']"));
        txtSurName.sendKeys("Patel");

        WebElement txtMobNumberEmail=driver.findElement(By.xpath("//input[@name='reg_email__']"));
        txtMobNumberEmail.sendKeys("menparasonal9@gmail.com");

        WebElement txtMobNumberEmailConfirmation=driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        txtMobNumberEmailConfirmation.sendKeys("menparasonal9@gmail.com");

        WebElement txtPassword=driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        txtPassword.sendKeys("sonal041993");

        WebElement dayDropDown= driver.findElement(By.id("day"));
        Select dd=new Select(dayDropDown);
        dd.selectByValue("4");

        FluentWait<RemoteWebDriver> fluentWait=new FluentWait<RemoteWebDriver>(driver);
        fluentWait.withTimeout(ofSeconds(30));
        fluentWait.pollingEvery(ofSeconds(5));
        WebElement monthDropDown= fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id("month")));
        Select mm=new Select(monthDropDown);
        mm.selectByIndex(4);

        WebElement yearDropDown= driver.findElement(By.id ("year"));
        Select yy=new Select(yearDropDown);
        yy.selectByVisibleText("1999");

        WebElement btnMale=driver.findElement(By.xpath("//label[text()='Female']"));
        btnMale.click();

        //FluentWait<WebDriver> fluentWait2=new FluentWait<>(driver);//a1
        FluentWait<WebDriver> fluentWait2=new WebDriverWait(driver,10);//a2
        List<WebElement> allDays=new WebDriverWait(driver,10).pollingEvery(ofSeconds(6)).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='day']/option"),31)); //a2
        //fluentWait2.withTimeout(Duration.ofSeconds(200));//a1
        fluentWait2.pollingEvery(ofSeconds(5));
        //List<WebElement> allDays = fluentWait2.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//select[@id='day']/option"),31));//a1
        allDays.get(16).click();

        FluentWait<RemoteWebDriver> fluentWait3=new FluentWait<RemoteWebDriver>(driver);
        fluentWait3.withTimeout(ofSeconds(200));
        fluentWait3.pollingEvery(ofSeconds(5));
        List<WebElement> allMonths = new FluentWait<WebDriver> (driver).withTimeout(ofSeconds(200)).pollingEvery(ofSeconds(6)).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//select[@id='month']/option"),12));
        for(WebElement month:allMonths){
            month.getAttribute("value").equals("6");
            month.click();
            break;
        }

        List<WebElement> allYears = driver.findElements(By.xpath("//select[@id='year']/option"));
        for(WebElement year:allYears){
            year.getAttribute("text").equals("2022");
            year.click();
            break;
        }

        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            throw new RuntimeException();
        }
    }
    /*@Test
    public void SnapDealExample() throws InterruptedException {
        //RemoteWebDriver driver= BrowserFactory.OpenBrowser("chrome");
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/&quot");
        driver.manage().window().maximize();

        Actions action = new Actions(driver);
        JavascriptExecutor js=(JavascriptExecutor)driver;

        WebElement checkboxesStorage;

        WebElement menuIconBar=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='menuIconBar']")));
        menuIconBar.click();

        WebElement linkComputerGaming=new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[normalize-space()='Computers & Gaming']")));
        action.moveToElement(linkComputerGaming).perform();
        linkComputerGaming.click();

        WebElement storageLink=new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Storage']")));

        action.moveToElement(storageLink).perform();
        storageLink.click();

        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

        Thread.sleep(3000);

        WebElement storageFilterName=new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@data-filtername,'Capacity_s')]//div[contains(@class,'filter-type-name lfloat')]")));
        js.executeScript("arguments[0].scrollIntoView(true);",storageFilterName );

        WebElement btnStorageViewMore=new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'View More ') and @data-filtername='Capacity_s' ]")));

        btnStorageViewMore.click();

        List<WebElement> filters;
        filters = driver.findElements(By.cssSelector("div[data-name='Capacity_s'] label"));

        for (int i = 0; i < filters.size(); i++) {
            String value;
            value = filters.get(i).getText().split("\n")[0];

            checkboxesStorage = new WebDriverWait(driver, 60)
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + value + "')]/parent::label")));

            checkboxesStorage = new WebDriverWait(driver, 60)
                        .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + value + "')]/parent::label")));

            boolean isSelected = checkboxesStorage.isSelected();
            if (isSelected == false && value.equals("4 GB") || value.equals("4GB") || value.equals("32 GB") || value.equals("32GB")) {
                checkboxesStorage.click();
            }
        }

       /* WebElement checkboxesStorage=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'4GB')]/parent::label")));
        checkboxesStorage.click();

        WebElement breadCrumb=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'clear-filter-pill  ')  and @data-value='32GB']")));

        Assert.assertTrue(breadCrumb.isEnabled());

        WebElement breadCrumbCloseBtn=new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //div[contains(@class,'filters-top-selected')]//span[contains(@class,'sd-icon sd-icon-delete-sign marL5')]")));

        breadCrumbCloseBtn.click();
    }*/
}
