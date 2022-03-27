package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
glue = {"StepDefinitions"},
monochrome = true,
//plugin = {"pretty", "junit:target/test-report.html"})
plugin = {"pretty", "html:target/cucumbe-reports.html"})
public class TestRunner {

}


//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources/Features",
//        glue = {"StepDefinitions"},
//        monochrome = true,
//        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"})
//public class TestRunner {

//}
