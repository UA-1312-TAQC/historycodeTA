package com.historycode.ui;

import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class ChronologyTest extends BaseTestRunner {


    @Issue("91")
    @Test
    @Step("Navigate to Роман Рáтушний «Сенека» Page")
    public void testStreetCodesPageNavigation() {

        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "catalog";
        driver.get(fullUrl);

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

    }

    @Issue("91")
    @Test
    @Step("Navigate to Chronology Block")
    public void testChronologyNavigation() {

    }
}