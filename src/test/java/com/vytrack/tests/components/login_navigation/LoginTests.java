package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
// ******************************************TEST CASE: Login test (positive)******************************************
public class LoginTests {
    WebDriver driver;  // WebDriver is an interface, it lets me execute my tests against different browsers


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test (priority = 1)
    public void positiveLoginTest1() {
        //********* 1 *********
        //Login	to Vytrack	as a store manager
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager216");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(2);

        //********* 2 *********
        //Verify name of the store manager is displayed on top right
        WebElement storeManagerName = driver.findElement(By.xpath("(//*[@class='dropdown-toggle'])[1]"));
        Assert.assertTrue(storeManagerName.getText().equals("Scot Ritchie"));
        SeleniumUtilities.waitPlease(2);

        //********* 3 *********
        //Verify Dashboad page is open
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='title title-level-2'])[1]")).click();
        SeleniumUtilities.waitPlease(3);
        Assert.assertTrue(driver.getTitle().equals("Dashboard - Dashboards"));

        //********* 4 *********
        //logout
        driver.findElement(By.xpath("(//*[@class='dropdown-toggle'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='no-hash']")).click();

        //********* 5 *********
        //Login	to Vytrack as a salesmanager
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager279");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        SeleniumUtilities.waitPlease(3);

        //********* 6 *********
        //Verify Dashboad page is open
        driver.findElement(By.xpath("(//*[@class='title title-level-1'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='title title-level-2'])[1]")).click();
        SeleniumUtilities.waitPlease(3);
        Assert.assertTrue(driver.getTitle().equals("Dashboard - Dashboards"));

        //********* 7 *********
        //A	different name should be displayed on top right
        WebElement storeManagerName2 = driver.findElement(By.xpath("(//*[@class='dropdown-toggle'])[1]"));
        Assert.assertTrue(storeManagerName2.getText().equals("Frankie Schneider"));
        SeleniumUtilities.waitPlease(2);

        //********* 8 *********
        //logout
        driver.findElement(By.xpath("(//*[@class='dropdown-toggle'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='no-hash']")).click();
    }
        //********* 9 *********
        //*********************************login to Vytrack as a driver*************************************
        @Test(priority = 2)
        public void positiveLoginTest2() {
            driver.findElement(By.id("prependedInput")).sendKeys("user183");
            driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
            driver.findElement(By.id("_submit")).click();
            SeleniumUtilities.waitPlease(3);

            //********* 10 *********
            //Verify Dashboad/Quick	Launchpad page is open
            WebElement launchpadText = driver.findElement(By.xpath("//*[@class='oro-subtitle' and contains(text(), 'Quick Launchpad')]"));
            Assert.assertTrue(launchpadText.getText().equals("Quick Launchpad"));

            //********* 11 *********
            //A	different name should be displayed on top right
            WebElement storeManagerName3 = driver.findElement(By.xpath("(//*[@class='dropdown-toggle'])[1]"));
            Assert.assertTrue(storeManagerName3.getText().equals("Selina Bergstrom"));
        }

    // ******************************************TEST CASE: Login test (negative)******************************************
    @Test (priority = 3)
    public void negativeLoginTest(){
        //********* STEP 1 *********
        //Enter	valid username and invalid password	information
        driver.findElement(By.id("prependedInput")).sendKeys("user27");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser");

        //********* STEP 2 *********
        //click Login
        driver.findElement(By.id("_submit")).click();
        Assert.assertTrue(driver.getTitle().equals("Login"));

        //********* STEP 3 *********
        //Message "Invalid user name or password." should be displayed
        WebElement message = driver.findElement(By.xpath("//*[@class='alert alert-error']"));
        String expected = "Invalid user name or password.";
        Assert.assertTrue(message.getText().equals(expected));
        SeleniumUtilities.waitPlease(2);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

