package com.booking;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        publish = true,
        features = "src/test/resources/features",   
        glue = {"com.booking.stepdefinitions"},
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        monochrome = true,
        tags="@HotelBooking"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
