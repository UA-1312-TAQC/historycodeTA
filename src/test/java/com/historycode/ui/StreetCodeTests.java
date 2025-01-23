package com.historycode.ui;

import com.historycode.ui.data_provider.StreetCodeDP;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StreetCodeTests extends BaseTestRunner {
    private static final int MAX_ALLOWED_TEASER_PARAGRAPHS = 2;
    private static final int MAX_SINGLE_TEASER_PARAGRAPH_LENGTH = 520;
    private static final int MAX_TWO_TEASER_PARAGRAPH_LENGTH = 455;

    private StreetCodePage streetCodePage;

    @Step("Navigate to the 'StreetCode' page")
    private void navigateToStreetCodePage(String addUIUrl) {
        driver.navigate().to(testValueProvider.getBaseUIUrl() + addUIUrl);
        streetCodePage = new StreetCodePage(driver);
    }

    @Issue("73")
    @Test(dataProvider = "urlTeaserSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification of the teaser text Length")
    public void testTeaserTextLength(String addPath) {

        navigateToStreetCodePage(addPath);

        int teaserParagraphCount = streetCodePage
                .getMainCard()
                .getTeaserParagraphCount();

        Assert.assertTrue(teaserParagraphCount <= MAX_ALLOWED_TEASER_PARAGRAPHS,
                String.format("The 'Teaser' text contains %d paragraphs (found) vs %d (expected maximum).",
                        teaserParagraphCount, MAX_ALLOWED_TEASER_PARAGRAPHS));

        int teaserCharacterCount = streetCodePage
                .getMainCard()
                .getTeaserCharacterCount();

        if (teaserParagraphCount == 1) {
            Assert.assertTrue(teaserCharacterCount <= MAX_SINGLE_TEASER_PARAGRAPH_LENGTH,
                    ("The 'Teaser' text is too long for one paragraph"));
        } else if (teaserParagraphCount == 2) {
            Assert.assertTrue(teaserCharacterCount <= MAX_TWO_TEASER_PARAGRAPH_LENGTH,
                    ("The 'Teaser' text is too long for two paragraphs"));
        }

        Assert.assertFalse(streetCodePage.getMainCard().isTeaserTextOverflowing(),
                ("The 'Teaser' text is displayed with truncation or overflow"));
    }

    @Issue("79")
    @Test(priority = 1)
    @Description("Verification that clicking the 'Donate' button displays a modal window with donation options.")
    public void testDonateButtonClick() {

        navigateToStreetCodePage("/sichovi-striltsi");

        DonateModal donateModal = streetCodePage
                .getQuickDonateButton()
                .clickDonateButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(donateModal.isFirstTitleDisplayed(), "The title of the modal window is not displayed.'");
        softAssert.assertTrue(donateModal.isAmountInputDisplayed(), "The manual amount input is not displayed.");
        softAssert.assertTrue(donateModal.areAmountButtonsDisplayed(), "The amount buttons are not displayed.");
        softAssert.assertTrue(donateModal.isDonateButtonDisplayed(), "The 'Donate' button is not displayed.");

        softAssert.assertAll();
    }

    @Issue("87")
    @Test(dataProvider = "urlWowFactSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification that if 3 or more facts are displayed, they scroll in a loop.")
    public void testWowFactsScroll(String addPath){
        final int ADDITIONAL_CARD = 2;

        navigateToStreetCodePage(addPath);

        SoftAssert softAssert = new SoftAssert();

        int countFactCard = streetCodePage
                .scrollToInterestingFacts()
                .getFacts()
                .getCarousel()
                .getCardCount();

        softAssert.assertTrue(countFactCard >= (3 + ADDITIONAL_CARD), "The carousel contains less than 3 cards.");

        String currentCardTitle = streetCodePage
                .getFacts()
                .getCarousel()
                .getCurrentNodeTitle();

        for (int i = 0; i < countFactCard - ADDITIONAL_CARD; i++) {
            streetCodePage
                    .getFacts()
                    .getCarousel()
                    .clickNextButton();
        }

        String afterScrollCurrentCardTitle = streetCodePage
                .getFacts()
                .getCarousel()
                .getCurrentNodeTitle();

        softAssert.assertEquals(currentCardTitle, afterScrollCurrentCardTitle,
                "The carousel is not scrolling in a loop.");

        softAssert.assertAll();
    }
}
