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
        HistoryMapComponent historyMapComponent = new StreetCodePage(driver)
                .getHistoryMapComponent();

        historyMapComponent
                .getMapTitleText()
        ;
        Assert.assertTrue(historyMapComponent.isStreetsCheckboxSelected(), "'Streets' checkbox was not selected!");
        historyMapComponent
                .clickStreetsCheckbox();

        Assert.assertFalse(historyMapComponent.isStreetsCheckboxSelected(), "'Streets' checkbox is selected!");

        historyMapComponent
                .clickHistoryCodesCheckbox();

        Assert.assertTrue(historyMapComponent.isHistoryCodesCheckboxSelected(), "'History Codes' checkbox was not selected!");

        historyMapComponent
                .clickInfoButton();
        Assert.assertTrue(historyMapComponent.isPopOverDisplayed(), "'Pop over' was not displayed!");

        historyMapComponent
                .getPopOverText();
        historyMapComponent
                .clickZoomIn();
        historyMapComponent
                .clickZoomIn();
        historyMapComponent
                .clickZoomOut();
    }

}
