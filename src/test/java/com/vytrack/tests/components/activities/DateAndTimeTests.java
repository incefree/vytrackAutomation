package com.vytrack.tests.components.activities;

import com.vytrack.utilities.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DateAndTimeTests extends TestBase {


    @Test
    public void verifyDateAndTime() {

        /**
         * 1. Log in as Valid user
         * 2. Go to Activities -> Calendar Events
         * 3. Click on create new calendar event
         * 4. Change the start date to future date
         * 5. Verify that end date changes to the same date
         * 6. Change back the start date to today's date
         * 7. Verify that end date changes back to today's date
         * */

        // read the username from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");

        // read the password from .properties file
        String password = ConfigurationReader.getProperty("salesmanagerpassword");

        // WE ALREADY GET THE DRIVER FROM TESTBASE

        // log in and go to calendar events
        VYTrackUtils.login(driver, username,password);
        VytrackUtilities.navigateToModule(driver,"Activities","Calendar Events");
        SeleniumUtilities.waitPlease(2);

        // create calendar event
        WebElement createEvent = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("createCalendar")));
        createEvent.click();
        SeleniumUtilities.waitPlease(2);

        // click start date
        WebElement startDate = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("startDateLocator")));
        startDate.click();
        SeleniumUtilities.waitPlease(2);

        // use select
        WebElement monthButton = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("selectMonth")));
        Select monthSelection = new Select(monthButton);

        // get month options
        List<WebElement> months = monthSelection.getOptions();

        String month = "Feb";

        // print months from the list, then breaks after getting "Feb"
        // we have to do break part because after getting "Feb" then cliking the "Feb", my month table will be gone
        // then it gives me nosuchelement exception
        for (WebElement each : months) {
            //System.out.println(each.getText());
            if(each.getText().equals(month)){
                each.click();
                // this part is important
                break;
            }
        }

        SeleniumUtilities.waitPlease(2);

        List<WebElement> selectDay = driver.findElements(By.cssSelector(ConfigurationReader.getProperty("selectDay")));

        String day = "10";
        for (WebElement days : selectDay) {
            if(days.getText().equals(day)){
                days.click();
                break;
            }
        }

//        THIS IS ANOTHER WAY TO PICK THE DAY
//
//        int desiredDay = 10;
//
//        selectDay.get(desiredDay - 1).click();

        SeleniumUtilities.waitPlease(2);

        WebElement endDate = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("endDateLocator")));

        Assert.assertEquals("Feb 10, 2019", startDate.getAttribute("value"));
        System.out.println("Start day is set to new date which is" + startDate.getAttribute("value"));

        Assert.assertEquals(endDate.getAttribute("value"), startDate.getAttribute("value"));
        System.out.println("Start Date: " + startDate.getAttribute("value") + "\nEnd Date: " + endDate.getAttribute("value"));

        SeleniumUtilities.waitPlease(2);

        // click the start date before locating todays date button
        startDate.click();
        WebElement todaysDate = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("todaysDateLocator")));
        todaysDate.click();

        //assert startDate.getAttribute("value").equals("Jul 29, 2019");
        Assert.assertEquals("Jul 29, 2019", startDate.getAttribute("value"));
        System.out.println("Start day is set to today's date which is " + startDate.getAttribute("value"));

        //assert startDate.getAttribute("value").equals(endDate.getAttribute("value"));
        Assert.assertEquals(endDate.getAttribute("value"), startDate.getAttribute("value"));
        System.out.println("Start Date: " + startDate.getAttribute("value") + "\nEnd Date: " + endDate.getAttribute("value"));

    }

    @Test
    public void autoAdjustTest(){
        /**
         Date Time, End time auto adjust
         1. Log in as Valid user
         2. Go to Activities -> Calendar Events
         3. Click on create new calendar event
         4. Change the start time to any other time
         5. Verify that end time changes exactly 1 hours later
         * */

        // read the username from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");

        // read the password from .properties file
        String password = ConfigurationReader.getProperty("salesmanagerpassword");

        // WE ALREADY GET THE DRIVER FROM TEST BASE

        // log in and go to calendar events
        VYTrackUtils.login(driver, username,password);
        VytrackUtilities.navigateToModule(driver,"Activities","Calendar Events");
        SeleniumUtilities.waitPlease(2);

        // create calendar event
        WebElement createEvent = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("createCalendar")));
        createEvent.click();
        SeleniumUtilities.waitPlease(2);

        WebElement startTimeButton = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("startTimeLocator")));
        startTimeButton.click();

        List<WebElement> times = driver.findElements(By.cssSelector(ConfigurationReader.getProperty("timetableLocator")));

        String desiredTime = "3:00 AM";
        for (WebElement each : times) {
            if (each.getText().equals(desiredTime)){
                each.click();
                break;
            }
        }

        SeleniumUtilities.waitPlease(2);

        WebElement endTimeButton = driver.findElement(By.cssSelector(ConfigurationReader.getProperty("endTimeLocator")));
        endTimeButton.getText();

        Assert.assertEquals("4:00 AM", endTimeButton.getAttribute("value"));

    }
}


