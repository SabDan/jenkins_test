package com.platform.project.commons;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( // linking code
		features = "src/test/resources/features",   // scenario
		glue = {"com.platform.project.steps"},	// steps- defines where the code is going to be written
		// plugin gives us a report of our tests
		plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html","json:target/cucumber-reports/cucumber.json"},
		monochrome = true, // more readable for the user
		tags = {"@tag1"} // will only run tag1
		
		)



public class TestRunner {

}
