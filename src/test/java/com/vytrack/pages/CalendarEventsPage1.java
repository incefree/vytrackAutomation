package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class CalendarEventsPage1 {

    public String createCalendarEvenLocator = "a[title^='Create Calendar']";
    public String startDateLocator = "input[class='input-small datepicker-input start hasDatepicker']";
    public String endDateLocator = "input[class='input-small datepicker-input end hasDatepicker']";
    public String endDateDayLocator = "a[class^='ui-state-default ui-state-active']";
    public String repeatsDropdownLocator = "select[id^='recurrence-repeats-view']";
    public String createCalendarEventName = "div[class='container-fluid page-title']>div>div>div>div>h1";
    public String moduleL = "ul[class='nav-multilevel main-menu']>li:nth-of-type(3)";
    public String startTimeLocator = "input[class='input-small timepicker-input start ui-timepicker-input']";
    public String endTimeLocator = "input[class='input-small timepicker-input end ui-timepicker-input']";
    public String selectedTimeLocator = "//div[@class='ui-timepicker-wrapper']//ul//li[3]";
    public String changed1130Locator = "//div[@class='ui-timepicker-wrapper']//ul//li[contains(text(),'11:30 PM')]";
    public String createCalendarEventBtnLocator = "a[title^='Create Calendar']";

    public String repeatCheckBoxLocator = "input[id^='recurrence-repeat-view']";
    public String radioButtonLocator = "label[class='fields-row']>input[checked='checked']";
    public String dailyValueLocator = "label[class='fields-row']>input[value='daily']+input";
    public String summaryLocator = "//div[@class='control-label wrap']/following-sibling::div[@data-name='recurrence-summary']//div//span";
    public String weekdayRadioButtonLocator = "//label[@data-role='control-section-switcher']//span[contains(text(),'Weekday')]";
    public String eMessage = "//span[@class='validation-failed']//span//span";


    //new..
    public String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);

        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);

        return todayStr;
    }


    //this method would return collection of repeat options


    public List<String> getRepeatOptions() {
        //we crated select object to work with dropdown
        Select select = new Select(Driver.getDriver().findElement(By.cssSelector(repeatsDropdownLocator)));
        //.getOptions(); return all available options in the dropdown.
        //every option is a webelement
        List<WebElement> options = select.getOptions();
        //this is a collection that will store text of every option
        List<String> optionsTextList = new ArrayList<>();
        for (WebElement option : options) {
            //go through every option and retrieve text
            //add that text into collection of text options
            optionsTextList.add(option.getText());
        }
        return optionsTextList;
    }


}


