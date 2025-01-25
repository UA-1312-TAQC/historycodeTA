package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetCodeButtonsTest extends BaseTestRunner {

    SoftAssert softAssert;
    StreetCodePage streetCodePage;

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
        softAssert = new SoftAssert();
        streetCodePage = new StreetCodePage(driver);

        pageUpButtonTest();
        donateButtonTest();

        // verify questionnaire does not appear at the bottom of the page

        //refresh page
        //verify questionnaire appears at the bottom of the page

        //verify questionnaire does not appear at the second time at the bottom of the page

        softAssert.assertAll();
    }

    @Step("Page UP button test")
    private void pageUpButtonTest() {
        streetCodePage.getVerticalProgress().clickSection(2);
        softAssert.assertTrue(streetCodePage.getScrollTopButton().isButtonDisplayed(), "Scroll top button is not visible");

        streetCodePage.getScrollTopButton().clickScrollTop();
        softAssert.assertTrue(streetCodePage.getMainCard().isNameVisible(), "Main card is not visible");
    }

    @Step("Donate button test")
    private void donateButtonTest() {
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(), "Donate button is not visible");

        DonateModal donateModal = streetCodePage.getQuickDonateButton().clickDonateButton();
        softAssert.assertTrue(donateModal.isDonateButtonDisplayed(), "Donate popup is not visible");

        // Підтримати unClicable
        // confirm agreement about personal data

        // Підтримати unClicable
        // Click on "500 грн"

        // Підтримати Clicable

        donateModal.close();
    }

    @Step("Questionnaire test")
    public void goToQuestionnaire() {
        streetCodePage.scrollToEndOfPage();
    }
}
