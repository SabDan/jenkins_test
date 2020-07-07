package com.platform.project.steps;

import org.openqa.selenium.WebDriver;

import com.platform.project.commons.AppiumDriverManager;
import com.platform.project.commons.Commons;
import com.platform.project.pageObjects.HomePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePageSteps {
	AppiumDriver<MobileElement> driver;
	HomePage homePage;
	AppiumDriverManager appiumDriverManager;
	
	@Before //before every test - this method will run
		public void Setup() {
			appiumDriverManager = new AppiumDriverManager();
			driver = appiumDriverManager.getDriver();
			homePage = new HomePage(driver);
			}
		
		
		@After
		public void tearDown() {
			driver.quit(); 
		}
		
			
		@And ("^I click on button 1$")
		public void clickButton1() {
			homePage.clickButton1();
			System.out.println("clicked on button 1");
		}
		
		@And ("^I click on button 2$")
		public void clickButton2() {
			homePage.clickButton2();
			System.out.println("clicked on button 2");
		}
		
		@And ("^I click on button 3$")
		public void clickButton3() {
			homePage.clickButton3();
			System.out.println("clicked on button 3");
		}
	}
