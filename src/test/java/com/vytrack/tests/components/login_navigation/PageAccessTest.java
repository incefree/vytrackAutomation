package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VytrackUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PageAccessTest extends TestBase {

    WebDriver driver;
    String errorMessageLocator = "//div[contains(text(),'You do not have permission to perform this action.')]";

    @Test(priority = 0)
    public void storeManagerAccessTest() {
        VytrackUtilities.loginPage(driver, "storemanager57", "UserUser123");
        VytrackUtilities.waitPlease(3);
        VytrackUtilities.navigateToModule(driver, "Fleet", "Vehicle Contracts");
        VytrackUtilities.waitPlease(3);
        VytrackUtilities.pageNameVerification(driver, "All Vehicle Contract");
    }

    @Test(priority = 1)
    public void salesManagerAccessTest() {
        VytrackUtilities.loginPage(driver, "salesmanager110", "UserUser123");
        VytrackUtilities.waitPlease(3);
        VytrackUtilities.navigateToModule(driver, "Fleet", "Vehicle Contracts");
        VytrackUtilities.waitPlease(3);
        VytrackUtilities.pageNameVerification(driver, "All Vehicle Contract");
    }

    @Test(priority = 2)
    public void truckDriverAccessTest(){
        VytrackUtilities.loginPage(driver, "user10", "UserUser123");
        VytrackUtilities.waitPlease(3);
        VytrackUtilities.navigateToModule(driver, "Fleet", "Vehicle Contracts");
        VytrackUtilities.waitPlease(3);
        String errorMessage = Driver.getDriver().findElement(By.xpath(errorMessageLocator)).getText();
        if(errorMessage.equals("You do not have permission to perform this action.")){
            System.out.println("Passed, the truck driver can not access the Vehicle Contracts.");
        }else{
            System.out.println("Failed!");
        }

    }

}
