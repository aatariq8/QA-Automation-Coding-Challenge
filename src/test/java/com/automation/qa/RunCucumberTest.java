package com.automation.qa;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
features = {"src/test/resources/features/"}, glue = {"com.automation.qa"}, dryRun = false,format = { "pretty", "html:target/cucumber" })
public class RunCucumberTest {


}
