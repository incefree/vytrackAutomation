package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.BrowserFactory;
import com.vytrack.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
// ******************************************TEST CASE: Menu options, store manager******************************************
public class SmokeTest_02 {
    WebDriver driver = BrowserFactory.getDriver("chrome");
    Actions action = new Actions(driver);

    @BeforeMethod
    public void setUp(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test
    public void storeManagerTest(){
        //==========================================Step-1===================================
        // Login to Vytrack as a store manager
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager216");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(3);
        //==========================================Step-2===================================
        // Navigate to Dashboards -> Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
        WebElement dashboards = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[1]"));
        action.moveToElement(dashboards);
        SeleniumUtilities.waitPlease(3);
        WebElement dashboard = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[1]"));
        action.moveToElement(dashboard);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle = "Dashboard - Dashboards";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName = "Dashboard";
        String actualPageName = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName,expectedPageName);

        //==========================================Step-3======================================
        // Navigate to Fleet -> Vehicles, verify page title Car - Entities - System - Car - Entities – System, page name Cars
        WebElement Fleet = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        action.moveToElement(Fleet);
        SeleniumUtilities.waitPlease(3);
        WebElement Vehicles = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[3]"));
        action.moveToElement(Vehicles);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle1 = "All - Car - Entities - System - Car - Entities - System";
        String actualPageTitle1 = driver.getTitle();
        Assert.assertEquals(actualPageTitle1,expectedPageTitle1);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName1 = "All Cars";
        String actualPageName1 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName1,expectedPageName1);

        //==========================================Step-4======================================
        // Navigate to Customers -> Accounts, verify page title Accounts - Customers, verify page name Accounts
        WebElement Customers = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
        action.moveToElement(Customers);
        SeleniumUtilities.waitPlease(3);
        WebElement Accounts = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[10]"));
        action.moveToElement(Accounts);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle2 = "All - Accounts - Customers";
        String actualPageTitle2 = driver.getTitle();
        Assert.assertEquals(actualPageTitle2,expectedPageTitle2);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName2 = "All Accounts";
        String actualPageName2 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName2,expectedPageName2);

        //==========================================Step-5======================================
        // Navigate to Customers -> Contacts, verify page title Accounts - Customers, verify page name Contacts
        WebElement Customers2 = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
        action.moveToElement(Customers2);
        SeleniumUtilities.waitPlease(3);
        WebElement Contacts = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[11]"));
        action.moveToElement(Contacts);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle3 = "All - Contacts - Customers";
        String actualPageTitle3 = driver.getTitle();
        Assert.assertEquals(actualPageTitle3,expectedPageTitle3);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName3 = "All Contacts";
        String actualPageName3 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName3,expectedPageName3);

        //==========================================Step-6======================================
        // Navigate to Sales -> Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
        WebElement Sales = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[4]"));
        action.moveToElement(Sales);
        SeleniumUtilities.waitPlease(3);
        WebElement Opportunities = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[12]"));
        action.moveToElement(Opportunities);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle4 = "Open Opportunities - Opportunities - Sales";
        String actualPageTitle4 = driver.getTitle();
        Assert.assertEquals(actualPageTitle4,expectedPageTitle4);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName4 = "Open Opportunities";
        String actualPageName4 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName4,expectedPageName4);

        //==========================================Step-7======================================
        // Navigate to Activities -> Calls verify page title Calendar Events - Activities, page name All Calls
        WebElement Activities = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        action.moveToElement(Activities);
        SeleniumUtilities.waitPlease(3);
        WebElement Calls = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[13]"));
        action.moveToElement(Calls);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle5 = "All - Calls - Activities";
        String actualPageTitle5 = driver.getTitle();
        Assert.assertEquals(actualPageTitle5,expectedPageTitle5);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName5 = "All Calls";
        String actualPageName5 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName5,expectedPageName5);

        //==========================================Step-8======================================
        // Navigate to Activities -> Calendar Events, verify page title Calendar Events - Activities, page name Calendar Events
        WebElement Activities2 = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        action.moveToElement(Activities2);
        SeleniumUtilities.waitPlease(3);
        WebElement CalendarEvents = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[14]"));
        action.moveToElement(CalendarEvents);
        action.click().build().perform();
        SeleniumUtilities.waitPlease(3);

        String expectedPageTitle6 = "All - Calendar Events - Activities";
        String actualPageTitle6 = driver.getTitle();
        Assert.assertEquals(actualPageTitle6,expectedPageTitle6);
        SeleniumUtilities.waitPlease(3);

        String expectedPageName6 = "All Calendar Events";
        String actualPageName6 = driver.findElement(By.xpath("(//h1[@class='oro-subtitle'])")).getText();
        Assert.assertEquals(actualPageName6,expectedPageName6);


    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

