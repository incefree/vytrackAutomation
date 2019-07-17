package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PageAccessTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test (priority = 1)
    public void storeManagerAccessTest(){
        //********* 1 *********
        //Login	to Vytrack as a	store manager
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager216");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(3);

        //********* 2 *********
        //Verify that you can access Vehicle contracts page
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[2]")).click();
        driver.findElement(By.xpath("(//*[@class='title title-level-2'])[6]")).click();
        SeleniumUtilities.waitPlease(2);
        Assert.assertTrue(driver.getTitle().equals("All - Vehicle Contract - Entities - System - Car - Entities - System"));

    }

    @Test (priority = 2)
    public void salesManagerAccessTest(){
        //********* 1 *********
        ////Login	to Vytrack as a	sales manager
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager279");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(3);

        //********* 2 *********
        //Verify that you can access Vehicle contracts page
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[2]")).click();
        driver.findElement(By.xpath("(//*[@class='title title-level-2'])[6]")).click();
        SeleniumUtilities.waitPlease(2);
        Assert.assertTrue(driver.getTitle().equals("All - Vehicle Contract - Entities - System - Car - Entities - System"));

    }

    @Test (priority = 3)
    public void driverAccessTest(){
        //********* 1 *********
        //Login	to	Vytrack	as a driver
        driver.findElement(By.id("prependedInput")).sendKeys("user183");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(3);

        //********* 2 *********
        //Verify that you cannot access	Vehicle	contracts page
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='title title-level-2'])[4]")).click();
        SeleniumUtilities.waitPlease(5);

        //********* 3 *********
        //Message "You do not have permission to perform this action." should be displayed
        WebElement message = driver.findElement(By.xpath("(//*[@class='message'])[2]"));
        SeleniumUtilities.waitPlease(5);
        Assert.assertTrue(message.getText().equals("You do not have permission to perform this action."));


    }


    @AfterMethod
    public void close(){
        SeleniumUtilities.waitPlease(1);
        driver.close();
    }
}

