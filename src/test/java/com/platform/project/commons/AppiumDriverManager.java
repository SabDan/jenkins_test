package com.platform.project.commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class AppiumDriverManager {

	private AppiumDriver<MobileElement> driver;
	private Logger logger = Logger.getLogger(AppiumDriverManager.class);
	HashMap<String, DesiredCapabilities> devices = new HashMap<String, DesiredCapabilities>();
	
	
	
	private AppiumDriver<MobileElement> createDriver(){
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2, Api 27");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
		caps.setCapability(MobileCapabilityType.APP,"/Users/sabrinahandsome/Downloads/example.apk");

		
		// to connect to Appium Server
		try {
			URL url = new URL("https://0.0.0:4723/wd/hub");
			driver = new AndroidDriver<>(url,caps); // connecting to server, emulator,download the app, launch the app
			return driver;
			
		}catch(MalformedURLException mue) {
			mue.printStackTrace();
			logger.info("Unable to connect to the Appium server");
			return null;
		}
		  

	}
	public AppiumDriver<MobileElement> getDriver(){ // getting a driver if not created
		if(driver == null) { 
			try {
				driver = createDriver();
				logger.info("Driver initialization is successful");
			} catch(Exception ex) {
				ex.printStackTrace();
				logger.info("Couldn't initalize driver");
			}
			
		} else {
			logger.info("Driver is already initialize");
		}
		return driver;
	}
}
