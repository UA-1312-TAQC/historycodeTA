package com.historycode.cucumber.contexts;

import com.historycode.TestValueProvider;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

public class ScenarioContext {

    @Getter
    @Setter
    private static WebDriver driver;
    @Getter
    @Setter
    private static TestValueProvider provider = new TestValueProvider();

}
