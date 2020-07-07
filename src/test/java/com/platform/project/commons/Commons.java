package com.platform.project.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.google.common.base.Function;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import static java.lang.Thread.currentThread;



public class Commons { // looks at cmd line, look for key, get the value for it
	private static Logger logger = Logger.getLogger(Commons.class); // replaces console logs Logger is a class
	
	public static String getElementText(WebDriver driver, WebElement el, int seconds) { 
		
		if(waitForElement(driver, el, seconds)) {
			logger.info("Home Page Title text is: "+ el.getText());
			return el.getText();
		} else {
			logger.info("Couldn't find element's  text");
			return "";
		}
	}
	
//	public static String getElementText(WebDriver driver, WebElement el) { // connecting to LoginPage
//		return getElementText(driver, el, 2);
//	}
	

 	public static void clickButton(AppiumDriver<MobileElement>driver, WebElement el, int seconds) {
// 		if (waitForElement(driver, el, seconds)) {
// 			logger.info("Clicking on button " + el.getText());
// 			el.click();
// 		}else {
// 			logger.info("Couldn't click on the button");
// 			check(false, driver, "Couldn't click on the button");
// 		}
 		
 		check(isElementVisible(driver, el, seconds), driver, "Couldn't click on the button");
 		logger.info("Clicking on button " + el.getText()); // clicking on account button
		el.click();
 	}
 	
 	
 	public static void enterEmail(WebDriver driver, WebElement el) {
 		el.sendKeys("ecalix@test.com");
 		logger.info("Email has been added");
 	}
 	
 	public static void enterPassword(WebDriver driver, WebElement el) {
 		el.sendKeys("test123");
 		logger.info("Info has been added");
 	}
 		
	
	// Reading from the terminal
	public static String createEnvVariable(String envVariableName, String defaultValue) { //envVariableName is the key
		String value = System.getProperty(envVariableName); // value in config - value will change - reading from term
		logger.info("Enviorment value for " + envVariableName + "is " + value);
		//return value; // returning chrome
		return value != null ? value : defaultValue; 
	}
	
	private static void screenShot(AppiumDriver<MobileElement>driver,String className, String methodName) {
		// naming the screenshots
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss_").format(new Date()); // order for screenshots
		String fileName = timeStamp + className + "_" + methodName + ".png";
		File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // save screenshot
		
		try {
			FileUtils.copyFile(ss, new File( "./screenshhots/" + fileName)); // copy the screenshot to a file
			logger.info("Screenshot " + fileName + "taken");
			
		}catch(IOException ioe) {
			logger.info("Unable to take screenshot " + fileName);
			ioe.printStackTrace();
			
		}
		
	}
	
	public static void check(boolean condition, AppiumDriver<MobileElement>driver, String failMessage) { // value for failMessage is being supplied from HomePageTest
		// checking for error message, if an element is visible on the page
		if(condition) {
			Assert.assertTrue(true);
		} else {
			logger.info(failMessage);
		//screenShot(driver, "class", "method");
			screenShot(driver, currentThread().getStackTrace()[2].getClassName(),currentThread().getStackTrace()[2].getMethodName());

			Assert.fail();
		}
	}
	
	
	
	
	public static boolean waitForElement(WebDriver driver, WebElement el, int seconds) {
		// waiting for a certain element
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			//wait.until throws an exception when timer expires
			wait.until(ExpectedConditions.visibilityOf(el)); // waiting for a certain condition to be satisfied
			logger.info("Element " + el.getText() + " is visible!!!!"); // Sign In sign is visible
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Element is NOT visible");
			return false;
		}
	}
		// Another way of explicit
		public static boolean isElementVisible(AppiumDriver<MobileElement>driver, WebElement el, int seconds) {
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(seconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS);
				
			
			Function<WebDriver, Boolean> function = arg0 -> { // LAMBDA EXPRESSIONS
				try {
					// overriding the implicit wait in WebDriverManager
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS); // want to get as close to 0 as possible
					el.getSize();
					return true; // means element is visible
				}catch (NoSuchElementException nsee) {
						return false; // if false - wait 10 milliseconds - function is run again
					}
				};
			
				
			try {
				//wait.until throws an exception when timer expires. if this is satisfied before 20 seconds then this will stop executing
				wait.until(function);// waiting for a certain condition to be satisfied
				logger.info("Element " + el.getText() + " is $$$ visible");
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				logger.info("Element is NOT visible");
				return false;
			}
		
	}
	
}
