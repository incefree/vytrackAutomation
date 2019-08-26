package com.vytrack.tests.components.calendar;

import com.vytrack.pages.calendar.CalendarEventsPage3;
import com.vytrack.pages.dashboards.DashboardPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalendarEventsTest3 extends TestBase {

    CalendarEventsPage3 calendarEventsPage= new CalendarEventsPage3();

    LoginPage loginPage =new LoginPage();

    DashboardPage dashboardPage =new DashboardPage();

/*1)Date Time, End date auto adjust
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start date to future date
5. Verify that end date changes to the same date
6. Change back the start date to today’s date
7. Verify that end date changes back to today’s date*/

    public String today;

    @Test
    public void DateTimeEndDateAutoAdjustTest(){

        today = calendarEventsPage.getCurrentDay();

        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VYTrackUtils.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Change the start date to future date
        WebElement datePicker=Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.startDateLocator));
        datePicker.click();datePicker.clear();datePicker.sendKeys("Aug 25, 2019",Keys.TAB,Keys.TAB);
        VytrackUtilities.waitForVisibility(By.cssSelector(calendarEventsPage.endDateDayLocator),5);

        //5.Verify that end date changes to the same date
        String endDateNumber = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.endDateDayLocator)).getText();
        Assert.assertEquals("25",endDateNumber);

        //6.change back to the today's date
        today=calendarEventsPage.getCurrentDay();
        VytrackUtilities.clickWithJS(datePicker);
        datePicker.clear();datePicker.sendKeys("Jul "+today+", 2019" );
        VytrackUtilities.waitForVisibility(By.cssSelector(calendarEventsPage.endDateDayLocator),5);

        //7.Verify that end date changes back to today's date
        String endDateDay1 = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.endDateDayLocator)).getText();
        Assert.assertEquals(today,endDateDay1);
    }

/*2) Date Time, End time auto adjust
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start time to any other time
5. Verify that end time changes exactly 1 hours later*/



    @Test
    public void dateTimeEndTimeAutoAdjustTest(){
        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VytrackUtilities.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Change start time to any other time
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.startTimeLocator)).click();
        VytrackUtilities.waitForVisibility(By.xpath(calendarEventsPage.selectedTimeLocator),3);
        WebElement startTime =Driver.getDriver().findElement(By.xpath(calendarEventsPage.selectedTimeLocator));
        String value = startTime.getText(); startTime.click();
        VytrackUtilities.waitForVisibility(By.cssSelector(calendarEventsPage.endTimeLocator),3);

        //5. Verify that end time changes exactly 1 hours later*/
        String  actualTime = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.endTimeLocator)).getAttribute("value");
        value = value.replaceAll("\\D+","");
        int num1 = Integer.parseInt(value);
        actualTime = actualTime.replaceAll("\\D+","");
        int num2 = Integer.parseInt(actualTime);
        Assert.assertTrue(num2-num1==100);
    }

/*3) Date Time, End date/time auto adjust
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start time to 11.30 PM
5. Verify that end date shows tomorrows date
6. Verify that end time is 12:30 AM*/

    @Test
    public void dateTimeEndDateTimeAutoAdjust(){
        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VytrackUtilities.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Change start time to any other time
        WebElement startTime= Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.startTimeLocator));
        startTime.click(); Driver.getDriver().findElement(By.xpath(calendarEventsPage.changed1130Locator)).click();
        VytrackUtilities.waitForVisibility(By.cssSelector(calendarEventsPage.endDateLocator),5);

        //5.Verify that end date shows tomorrows date
        String expectedDate = LocalDate.now().plus(1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("MMM d, yyy"));
        String actualDate = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.endDateLocator)).getAttribute("value");
        Assert.assertEquals(actualDate,expectedDate);

        //6.Verify that end time shows 12:30AM
        VytrackUtilities.waitForVisibility(By.cssSelector(calendarEventsPage.endTimeLocator),3);
        Assert.assertTrue(Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.endTimeLocator)).getAttribute("value").equals("12:30 AM"));
    }


/*Daily Repeat Tests
1) Daily repeat option, Repeat every, summary
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Verify that Daily is selected by default
6. Verify day(s) checkbox is selected and default value is 1
7. Verify summary says Daily every 1 day
8. Check the weekday checkbox
9. Verify that days input now disabled
10. Verify summary says Daily every weekday*/

    @Test
    public void dailyRepeatOptionTest1(){
        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VytrackUtilities.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Click on Repeat checkbox
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.repeatCheckBoxLocator)).click();
        VytrackUtilities.waitPlease(3);

        //5. Verify that Daily is selected by default
        Select select = new Select(Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.repeatsDropdownLocator)));
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Daily"));

        //6. Verify day(s) checkbox is selected and default value is 1
        WebElement radioButton = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.radioButtonLocator));
        Assert.assertTrue(radioButton.isEnabled());

        WebElement value = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.dailyValueLocator));
        Assert.assertTrue(value.getAttribute("value").equals("1"));

        //7. Verify summary says Daily every 1 day
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath(calendarEventsPage.summaryLocator)).getText().equals("Daily every 1 day"));

        //8. Check the weekday checkbox
        Driver.getDriver().findElement(By.xpath(calendarEventsPage.weekdayRadioButtonLocator)).click();
        VytrackUtilities.waitPlease(3);

        //9. Verify that days input now disabled
        Assert.assertTrue(value.isEnabled()==false);

        //10. Verify summary says Daily every weekday
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath(calendarEventsPage.summaryLocator)).getText().equals("Daily, every weekday"));

    }

/*2) Daily repeat option, Repeat every, default values
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Verify that Daily is selected by default
6. Verify day(s) checkbox is selected and default value is 1
7. Verify summary says Daily every 1 day*/

    @Test
    public void dailyRepeatOptionTest2(){
        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VytrackUtilities.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Click on Repeat checkbox
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.repeatCheckBoxLocator)).click();
        VytrackUtilities.waitPlease(3);

        //5. Verify that Daily is selected by default
        Select select = new Select(Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.repeatsDropdownLocator)));
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Daily"));

        //6. Verify day(s) checkbox is selected and default value is 1
        WebElement radioButton = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.radioButtonLocator));
        Assert.assertTrue(radioButton.isEnabled());

        WebElement value = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.dailyValueLocator));
        Assert.assertTrue(value.getAttribute("value").equals("1"));

        //7. Verify summary says Daily every 1 day
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath(calendarEventsPage.summaryLocator)).getText().equals("Daily every 1 day"));
    }

    /*3) Daily repeat option, Repeat every day(s), error messages
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Test the day(s) input entering different values (boundary value analysis)
6. Verify error messages The value have not to be less than 1. and The value have not to be
more than 99. occur when values are too big or small
7. Verify that error messages disappear when valid values are entered*/


    @Test
    public void dailyRepeatOptionTest3(){
        //1.Login as valid user(truck driver)
        loginPage.login(ConfigurationReader.getProperty("truckdriverusername"),ConfigurationReader.getProperty("truckpassword"));
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.moduleL),5);

        //2.Go to Activities->CalendarEvents
        dashboardPage.navigateToModule(driver, "Activities", "Calendar Events");
        VytrackUtilities.waitUntilLoaderScreenDisappear(Driver.getDriver());

        //3.Click on create new calendar event
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.createCalendarEvenLocator)).click();
        VytrackUtilities.clickWithWait(Driver.getDriver(),By.cssSelector(calendarEventsPage.createCalendarEventName),5 );

        //4.Click on Repeat checkbox
        Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.repeatCheckBoxLocator)).click();
        VytrackUtilities.waitPlease(3);

        //5. Test the day(s) input entering different values (boundary value analysis)
        //first enter -1
        WebElement valueBox = Driver.getDriver().findElement(By.cssSelector(calendarEventsPage.dailyValueLocator));
        valueBox.click(); valueBox.clear(); valueBox.sendKeys("-1");
        String errorMessage1 = Driver.getDriver().findElement(By.xpath(calendarEventsPage.eMessage)).getText();
        //clear and enter 100
        valueBox.click();valueBox.clear(); valueBox.sendKeys("100");
        String errorMessage2 = Driver.getDriver().findElement(By.xpath(calendarEventsPage.eMessage)).getText();

        //6. Verify error messages The value have not to be less than 1. and The value have not to be
        //more than 99. occur when values are too big or small
        Assert.assertTrue(errorMessage1.equals("The value have not to be less than 1."));
        Assert.assertTrue(errorMessage2.equals("The value have not to be more than 99."));

        //7. Verify that error messages disappear when valid values are entered
        valueBox.click(); valueBox.clear(); valueBox.sendKeys("10");
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//span[@class='validation-failed']")).getText().equals(""));

    }
/*4) Daily repeat option, Repeat every day(s), functionality
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Enter random value to the day(s) field
6. Verify that Summary says Daily every <random number> day
7. Enter another random value to the day(s) field
8. Verify that Summary updated with Daily every <random number> day
*5) Daily repeat option, blank fields
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Clear the value of the day(s) field
6. Message This value should not be blank. should come up
7. Enter a valid value to the day(s) field the
8. Message This value should not be blank. should disappear
9. Clear the value of the After occurrences field
10. Message This value should not be blank. should come up 11. Enter a valid value to the After occurrences field the
12. Message This value should not be blank. should disappear
*6) Daily repeat option, Ends, error messages
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Test the After occurrences input entering different values (boundary value analysis)
6. Verify error messages The value have not to be less than 1. and The value have not to be
more than 99. occur when values are too big or small
7. Verify that error messages disappear when valid values are entered
*7) Daily repeat option, Ends, functionality
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Click on Repeat checkbox
5. Enter random value to the After occurrences field
6. Verify that Summary says Daily every <random number> day
7. Enter another random value to the After occurrences field
8. Verify that Summary updated with Daily every <random number> day
*/

}