package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Assignment3_02 {

    static WebDriver driver;

    @Test
    public void SnapDealExample() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "browser_exes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/&quot");
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement menuIconBar = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='menuIconBar']")));
        menuIconBar.click();

        WebElement linkComputerGaming = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[normalize-space()='Computers & Gaming']")));
        actions.moveToElement(linkComputerGaming).perform();
        linkComputerGaming.click();

        WebElement storageLink = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Storage']")));

        actions.moveToElement(storageLink).perform();
        storageLink.click();

        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

        Thread.sleep(3000);

        WebElement storageFilterName = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@data-filtername,'Capacity_s')]//div[contains(@class,'filter-type-name lfloat')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", storageFilterName);

        WebElement btnStorageViewMore = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'View More ') and @data-filtername='Capacity_s' ]")));

        btnStorageViewMore.click();

        WebElement checkboxesStorage32_GB = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'32 GB')]/parent::label")));

        checkboxesStorage32_GB.click();

        WebElement btnStorageViewMore2= new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'View More ') and @data-filtername='Capacity_s' ]")));

        btnStorageViewMore2.click();

        WebElement storageFilterName2 = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@data-filtername,'Capacity_s')]//div[contains(@class,'filter-type-name lfloat')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", storageFilterName2);

        WebElement checkboxesStorage4GB = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'4GB')]/parent::label")));

        checkboxesStorage4GB.click();

        WebElement filterHeader = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='filters']")));

        js.executeScript("arguments[0].scrollIntoView(true);", filterHeader);

        Assert.assertTrue(filterHeader.isDisplayed());

        WebElement breadCrumb32_GB=new WebDriverWait(driver, 40)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[contains(@class,'clear-filter-pill  ')  and @data-value='32%20GB']")));

        js.executeScript("arguments[0].click()", breadCrumb32_GB);

        WebElement breadCrumb4GB=new WebDriverWait(driver, 40)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[contains(@class,'clear-filter-pill  ')  and @data-value='4GB']")));

        js.executeScript("arguments[0].click()", breadCrumb4GB);

        WebElement storageFilterName3 = new WebDriverWait(driver, 70)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@data-filtername,'Capacity_s')]//div[contains(@class,'filter-type-name lfloat')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", storageFilterName3);

        WebElement checkboxesStorage4GB1 = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'4GB')]/parent::label")));

        Assert.assertFalse(checkboxesStorage4GB1.isSelected());

        WebElement checkboxesStorage32_GB1 = new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'32 GB')]/parent::label")));

        Assert.assertFalse(checkboxesStorage32_GB1.isSelected());
    }
}
