package com.vytrack.utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestBase {
    // should be public/protected
    public WebDriver driver;
    public Actions action;

    // we need this object for building reports
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter; // builds reports
    protected ExtentTest extentLogger; // add all information to the report

    @BeforeTest
    public void testSetup(){
        // we are creating actual reporter
        report = new ExtentReports();
        String pathToReport = System.getProperty("user.dir") + "/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(pathToReport);

        report.attachReporter(htmlReporter);

        // we can add system information to report
        report.setSystemInfo("II",System.getProperty("ii.name"));


        htmlReporter.config().setDocumentTitle("VYTrack Test Automation");

    }


    @BeforeMethod
    public void setup() {



        driver = Driver.getDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {

            if (ITestResult.FAILURE == result.getStatus()) {
                //if test failed get a screenshot and save the location to the image
                String pathToTheScreenshot = SeleniumUtilities.getScreenshot(result.getName());

                // capture the name of a test method that failed
                extentLogger.fail(result.getName());
                try {
                    //to add screenshot into report
                    extentLogger.addScreenCaptureFromPath(pathToTheScreenshot);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //to add thrown exception into report
                extentLogger.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                //if test skipped, this information will appear on the report
                extentLogger.skip("Test case skipped " + result.getName());
            }
            Driver.closeDriver();
        }
    }
    @AfterTest
    public void tearDownTest(){
        report.flush();
    }
}