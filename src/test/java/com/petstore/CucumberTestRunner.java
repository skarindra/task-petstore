package com.petstore;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by sekarayukarindra
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/site/serenity/cucumber.json"},
        features = "src/test/resources/features"
)
public class CucumberTestRunner {
}
