package com.ali.quasar;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = "com.ali.quasar",
        plugin = {"json:target/cucumber.json", "pretty", "html:target/cucumber", "timeline:target/timeline"}
)
public class RunCucumberIT {


}

