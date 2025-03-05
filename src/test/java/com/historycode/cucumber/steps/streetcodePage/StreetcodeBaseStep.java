package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import io.cucumber.java.en.Given;

public class StreetcodeBaseStep extends BaseStep {

    @Given("the StreetCode page is opened")
    public void theStreetCodePageIsOpen() {
        initDriver();
        driver.get(provider.getBaseUIUrl() + "/roman-ratushnyi-seneka");
        StreetCodePage streetCodePage = new StreetCodePage(driver);
    }
}
