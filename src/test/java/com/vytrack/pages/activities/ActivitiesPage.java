package com.vytrack.pages.activities;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivitiesPage {

    public ActivitiesPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "(//*[contains(text(),'Create Calendar event')])[3]")
    public WebElement createCalendarEventButton;

    @FindBy(xpath = "//input[contains(@id,'recurrence-repeat-view')]")
    public WebElement createFormRepeatCheckbox;

    @FindBy(xpath = "//select[contains(@id,'recurrence-repeats-view')]")
    public WebElement createFormRepeatsSelectbox;

    @FindBy(css = ".recurrence-subview-control__item>label>input")
    public WebElement createFormRepeatEveryDaysRadioButton;

    @FindBy(css = ".recurrence-subview-control__item:nth-of-type(2)>label>input")
    public WebElement createFormRepeatEveryWeekDayRadioButton;

    @FindBy(css = ".fields-row>input:nth-of-type(3)")
    public WebElement createFormRepeatEveryDaysInputBox;

    @FindBy(css = ".control-group.recurrence-summary.alert-info>div:nth-of-type(2)>div>span")
    public WebElement createFormSummary;


}
