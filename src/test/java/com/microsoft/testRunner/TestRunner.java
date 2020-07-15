package com.microsoft.testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C://Users//Muril//eclipse-workspace//SDETInterviewChallengeProjectTest//Features//Challenge.feature",
		glue = "com.microsoft.steps")

public class TestRunner { 
	
}