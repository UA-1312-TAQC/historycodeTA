package com.historycode.api.testRunners;

import com.historycode.TestValueProvider;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeSuite;

public class ApiTestRunner {
    protected static TestValueProvider testValueProvider;

    @BeforeSuite
    public void setUp() {
        testValueProvider = new TestValueProvider();
        RestAssured.registerParser("application/problem+json", Parser.JSON);
    }

}
