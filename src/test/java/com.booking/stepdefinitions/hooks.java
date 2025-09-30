package com.booking.stepdefinitions;

import base.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class hooks {

@Before
    public void setup() {
    ConfigReader.getproperty("appURL");
    }
}
