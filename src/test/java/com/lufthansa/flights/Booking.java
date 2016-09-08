package com.lufthansa.flights;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClasses.FlightsPage;
import utilities.ScreenShot;

public class Booking {
	
	WebDriver driver;
	FlightsPage fp;
	ExtentTest test;
	ExtentReports report;
	String baseUrl;
	
  
  @BeforeClass
  public void beforeClass() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Timmy\\workspace\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  baseUrl = "http://www.lufthansa.com/uk/en/Homepage";
	  driver.get(baseUrl);
	  report = new ExtentReports("C:\\Users\\Timmy\\LUFTHANSA\\Logs\\report.html", false);
	  test = report.startTest("Lufthansa Bookings");
	  fp = new FlightsPage(driver, test);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
  }
	
  @Test 
  public void oneWayTest()  {
	  fp.doOneWayFlight("London", "Dubai", "15", "3", "5", "4", "F");
	  fp.startSearch();
  }
  
  
  @Test
  public void roundTripTest() throws InterruptedException{
	  fp.doRoundTripFlight("London", "Dubai", "15","26", "3", "5", "4", "B");
	  fp.startSearch();
  }
  
  
  @AfterMethod
  public void afterMethod(ITestResult iTResult) throws IOException {
	  if (iTResult.getStatus() ==  ITestResult.FAILURE) {
		  
		 String path = ScreenShot.takeScreentshot(driver, "booking failed");
		 String imagePath = test.addScreenCapture(path);
		 test.log(LogStatus.FAIL, "One way booking failed...",imagePath);
		 
	}
  }



  @AfterClass
  public void afterClass() throws InterruptedException {
	  
	  Thread.sleep(2000);
	  driver.close();
	  report.flush();
	  report.endTest(test);
  }

}
