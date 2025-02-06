package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.HistoryMapComponent;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.lang.Thread.sleep;

public class StreetCodeHistoryMapTest extends BaseTestRunner {
    private HistoryMapComponent historyMapComponent;

    @BeforeMethod
    @Description("Navigate to StreetCode page")
    public void navigateToStreetCodePage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu()
                .goToStreetCodeCatalogPage();
        StreetCodeCatalogPage streetCodeCatalogPage = new StreetCodeCatalogPage(driver);
        streetCodeCatalogPage
                .clickCatalogItemByIndex(0);
        StreetCodePage streetCodePage = new StreetCodePage(driver);
        sleep(5000);
        streetCodePage
                .scrollUntilElementIsVisible(streetCodePage
                        .getMapRootElement());
    }


    @Test
    @Description("Verify that all map elements are present and can be used")
    public void testElements() {
        SoftAssert softAssert = new SoftAssert();

        HistoryMapComponent historyMapComponent = new StreetCodePage(driver)
                .getHistoryMapComponent();

        historyMapComponent.getMapTitleText();

        softAssert.assertTrue(historyMapComponent.isStreetsCheckboxSelected(), "'Streets' checkbox was not selected!");

        historyMapComponent.clickStreetsCheckbox();
        softAssert.assertFalse(historyMapComponent.isStreetsCheckboxSelected(), "'Streets' checkbox is still selected after clicking!");

        historyMapComponent.clickHistoryCodesCheckbox();
        softAssert.assertTrue(historyMapComponent.isHistoryCodesCheckboxSelected(), "'History Codes' checkbox was not selected!");

        historyMapComponent.clickInfoButton();
        softAssert.assertTrue(historyMapComponent.isPopOverDisplayed(), "'Popover' was not displayed!");

        historyMapComponent.getPopOverText();

        historyMapComponent.clickZoomIn();
        historyMapComponent.clickZoomIn();
        historyMapComponent.clickZoomOut();

        softAssert.assertAll();
    }
}
