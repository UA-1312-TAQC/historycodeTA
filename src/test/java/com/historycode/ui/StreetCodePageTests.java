package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.MainCardComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StreetCodePageTests extends BaseTestRunner {
    private StreetCodePage streetCodePage;
    private SoftAssert softAssert;

    @BeforeMethod
    @Step("Navigate to StreetCode page")
    public void navigateToStreetCodePage() {
        softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);

        homePage.scrollToElement(homePage.getPersonCarouselElement());

        homePage.waitUntilElementVisible(homePage.getPersonCarouselElement());

        homePage.waitUntilElementClickable(homePage.getPersonCarouselElement());
        homePage.getPersonsCarousel().getCarouselItems().get(0).clickMore();

        streetCodePage = new StreetCodePage(driver);
    }

    @Issue("70")
    @Test
    @Description("Verify that all interactive buttons are displayed")
    public void testInteractiveButtons() {
        //"Verify initial state of Donate button"
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(),
                "Quick Donate button should be visible initially");
        //"Verify Audio button state and functionality"
        MainCardComponent mainCard = streetCodePage.getMainCard();

        if (mainCard.isAudioAvailable()) {
            softAssert.assertTrue(mainCard.isAudioButtonEnabled(), "Audio button should be enabled");
            softAssert.assertEquals(mainCard.getAudioButtonText(), "Прослухати текст",
                    "Audio button should display 'Прослухати текст'");

            mainCard.toggleAudio();
        } else {
            softAssert.assertTrue(mainCard.isAudioComingSoonMessageDisplayed(),
                    "Button should display 'Аудіо на підході' and be disabled");
            softAssert.assertFalse(mainCard.isAudioButtonEnabled(),
                    "Audio button should be disabled");
        }

        // "Verify that Donate button remains sticky while scrolling"
        streetCodePage.getQuickDonateButton().scrollToEndOfPage();
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(),
                "Donate button should be visible after scrolling to bottom");
        softAssert.assertAll();
    }


}
