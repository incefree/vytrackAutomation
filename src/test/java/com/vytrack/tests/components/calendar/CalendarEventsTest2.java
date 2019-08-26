package com.vytrack.tests.components.calendar;

import com.vytrack.pages.calendar.CalendarEventsPage2;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarEventsTest2 extends TestBase {


    @Test
    public void verifyTitleColumn() {
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage2 calendarPage = new CalendarEventsPage2();
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //deselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        calendarPage.selectGridSetting("Title", false);
        SeleniumUtils.waitPlease(3);

        //Verify that title column name is not visible any more
        Assert.assertFalse(calendarPage.verifyHeaderExists("Title"), "Title column name still visible.");

        //to close grid settings menu
        calendarPage.gridSettingsElement.click();

        //select title option again
        calendarPage.selectGridSetting("Title", true);

        //Verify that title column name is visible again
        Assert.assertTrue(calendarPage.verifyHeaderExists("Title"), "Title column is not visible.");
    }
}