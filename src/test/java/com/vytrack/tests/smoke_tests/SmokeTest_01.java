package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
// ******************************************TEST CASE: Menu options, driver******************************************
public class SmokeTest_01 {
    //********* STEP 1 *********
    //Login	to Vytrack	as a Truck Driver

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

        driver.findElement(By.id("prependedInput")).sendKeys("user183");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(3);

    }

    //********* STEP 2 *********
    //Navigate	to	Fleet, Vehicles,
    // verify	page	title	Car - Entities - System - Car - Entities – System,
    // page name	Cars
    @Test(priority = 1)
    public void fleetVehicles() {
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[1]")).click();
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[1]")).click();
        SeleniumUtils.waitPlease(4);
        Assert.assertEquals(driver.getTitle(), "Car - Entities - System - Car - Entities - System");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(), "Cars");

    }

    //********* STEP 3 *********
    //Navigate	to	Customers, Accounts
    //verify page title	Accounts - Customers,
    // verify page name	Accounts
    @Test (priority = 2)
    public void customersAccounts() {
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[2]")).click();
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[8]")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(), "Accounts - Customers");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(), "Accounts");

    }
    //********* STEP 4 *********
    //Navigate	to	Customers,Contacts
    //verify page title	Accounts - Customers,
    // verify page name Contacts
    @Test (priority = 3)
    public void customersContacts() {
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]")).click();
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[9]")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(), "Contacts - Customers");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(),"Contacts");

    }

    //********* STEP 5 *********
    //Navigate	to	Activity,Calendar
    //verify page title	Calendar Events - Activities,
    // verify page Calendar Events
    @Test (priority = 4)
    public void activitiesCalendarEvents() {
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]")).click();
        driver.findElement(By.xpath("(//span[@class='title title-level-2'])[10]")).click();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(driver.getTitle(),"Calendar Events - Activities");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1[class='oro-subtitle']")).getText(),"Calendar Events");

    }


    @AfterClass
    public void close(){
        driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[1]")).click();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
        SeleniumUtils.waitPlease(2);
        driver.close();
    }
}


