package com.automation.qa;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
features = {"classpath:features/search-repositories.feature"}, glue = {"com.automation.qa.StepDefinitions"}, dryRun = false,format = { "pretty", "html:target/cucumber" })
public class RunCucumberTest {


}
