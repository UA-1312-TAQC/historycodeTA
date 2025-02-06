package com.historycode.api.streetcode;

import com.historycode.TestValueProvider;
import org.testng.annotations.BeforeSuite;

public class ApiTestRunner {
    protected  static TestValueProvider testValueProvider;

    @BeforeSuite
    public void setUp(){
        testValueProvider = new TestValueProvider();
    }
}
