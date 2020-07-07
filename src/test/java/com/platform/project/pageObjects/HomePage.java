package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.platform.project.commons.Commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePage {
	
	
	// page factory
	
	@FindBy(id="at.markushi.reveal:id/btn_1")
	WebElement button1; 
	
	@FindBy(id="at.markushi.reveal:id/btn_2")
	WebElement button2;
	
	@FindBy(id="at.markushi.reveal:id/btn_3")
	WebElement button3;
	
	
	private AppiumDriver<MobileElement> driver;
	private Logger logger = Logger.getLogger(HomePage.class); 

	
	public HomePage(AppiumDriver<MobileElement> driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void clickButton1() {
		logger.info("clicked on button 1");
		Commons.clickButton(driver, button1,1);
	}
	public void clickButton2() {
		logger.info("clicked on button 2");
		Commons.clickButton(driver, button2,2);
	}
	public void clickButton3() {
		logger.info("clicked on button 3");
		Commons.clickButton(driver, button3,3);
	}
	
}

























