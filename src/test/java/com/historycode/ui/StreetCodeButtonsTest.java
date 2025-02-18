package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import com.historycode.ui.page.streetCodePage.modals.SurveyModal;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetCodeButtonsTest extends BaseTestRunner {

    StreetCodePage streetCodePage;
    final int DONATE_AMOUNT = 500;

    @BeforeMethod
    @Step("Go to the first StreetCode page")
    public void goToStreetCode() {
        streetCodePage = new HomePage(driver)
                .openBurgerMenu()
                .goToStreetCodeCatalogPage()
                .clickCatalogItemByIndex(0);
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Page UP button test")
    public void pageUpButtonTest() {
        streetCodePage.getVerticalProgress().clickSection(2);
        Assert.assertTrue(streetCodePage.getScrollTopButton().isButtonDisplayed(), "Scroll top button is not visible");

        streetCodePage.getScrollTopButton().clickScrollTop();
        Assert.assertTrue(streetCodePage.getMainCard().isNameVisible(), "Scroll top button is not working");
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Donate button test")
    public void donateButtonTest() {
        Assert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(), "Donate button is not visible");

        DonateModal donateModal = streetCodePage.getQuickDonateButton().clickDonateButton();
        Assert.assertTrue(donateModal.isDonateButtonDisplayed(), "Donate popup is not visible");

        Assert.assertFalse(donateModal.isDonateButtonEnabled(), "Save button is enabled, 0/2 mandatory actions taken");
        donateModal.clickAgreeCheckbox();

        Assert.assertFalse(donateModal.isDonateButtonEnabled(), "Save button is enabled, 1/2 mandatory actions taken");
        donateModal.clickAmountButton(DONATE_AMOUNT);

        Assert.assertTrue(donateModal.isDonateButtonEnabled(), "Save button is not enabled, 2/2 mandatory actions taken");

        donateModal.clickCloseButton();
    }

    @Test
    @Issue("54")
    @Epic("StreetCode page")
    @Description("Questionnaire modal Test")
    public void questionnaireTest() {
        streetCodePage = new StreetCodePage(driver);
        streetCodePage.scrollToEndOfPage();
        SurveyModal surveyModal = streetCodePage.getSurveyModal();
        Assert.assertTrue(surveyModal.isDisplayed());
        surveyModal.clickCloseButton();

        streetCodePage.getScrollTopButton().clickScrollTop();
        streetCodePage.scrollToEndOfPage();
        Assert.assertFalse(surveyModal.isDisplayed());
    }
}
