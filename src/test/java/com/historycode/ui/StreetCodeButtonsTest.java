package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetCodeButtonsTest extends BaseTestRunner {

    @BeforeMethod
    public void goToStreetCode() {
        new HomePage(driver)
                .openBurgerMenu()
                .goToStreetCodeCatalogPage()
                .clickCatalogItemByIndex(0);
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Page UP and donate buttons")
    public void testStreetCodeButtons() {
        SoftAssert softAssert = new SoftAssert();
        StreetCodePage streetCodePage = new StreetCodePage(driver);

        streetCodePage.getVerticalProgress().clickSection(2);

        softAssert.assertTrue(streetCodePage.getScrollTopButton().isButtonDisplayed(), "Scroll top button is not visible");

        softAssert.assertAll();
    }
}
