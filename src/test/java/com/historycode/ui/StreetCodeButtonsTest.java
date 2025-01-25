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
    @Step("Go to the first StreetCode page")
    public void goToStreetCode() {
        softAssert = new SoftAssert();
        streetCodePage = new HomePage(driver)
                .openBurgerMenu()
                .goToStreetCodeCatalogPage()
                .clickCatalogItemByIndex(0);
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Page UP button test")
    private void pageUpButtonTest() {
        //todo: Vertical Progress is not working
        streetCodePage.getVerticalProgress().clickSection(2);
        softAssert.assertTrue(streetCodePage.getScrollTopButton().isButtonDisplayed(), "Scroll top button is not visible");

        streetCodePage.getScrollTopButton().clickScrollTop();
        softAssert.assertTrue(streetCodePage.getMainCard().isNameVisible(), "Scroll top button is not working");
        softAssert.assertAll();
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Donate button test")
    private void donateButtonTest() {
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(), "Donate button is not visible");

        DonateModal donateModal = streetCodePage.getQuickDonateButton().clickDonateButton();
        softAssert.assertTrue(donateModal.isDonateButtonDisplayed(), "Donate popup is not visible");

        softAssert.assertFalse(donateModal.getSaveButton().isEnabled(), "Save button is enabled, 0/2 mandatory actions taken");
        // confirm agreement about personal data

        softAssert.assertFalse(donateModal.getSaveButton().isEnabled(), "Save button is enabled, 1/2 mandatory actions taken");
        // Click on "500 грн"

        softAssert.assertTrue(donateModal.getSaveButton().isEnabled(), "Save button is not enabled, 2/2 mandatory actions taken");

        donateModal.close();
        softAssert.assertAll();
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Questionnaire modal Test")
    public void questionnaireTest() {
        streetCodePage.scrollToEndOfPage();
        //softAssert.assertTrue(streetCodePage.getQuestionnaire().isVisible());

        //streetCodePage.getQuestionnaire().clickCloseButton();
        streetCodePage.scrollToTop();
        streetCodePage.scrollToEndOfPage();
        //softAssert.assertFalse(streetCodePage.getQuestionnaire().isVisible());

        //streetCodePage.refreshPage();
        DonateModal donateModal = streetCodePage.getQuickDonateButton().clickDonateButton();
        donateModal.close();
        streetCodePage.scrollToEndOfPage();
        //softAssert.assertTrue(streetCodePage.getQuestionnaire().isVisible());

        softAssert.assertAll();
    }
}
