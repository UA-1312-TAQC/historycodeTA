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
        streetCodePage = new StreetCodePage(driver, false);
    }

    @Issue("73")
    @Test(dataProvider = "urlSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification of the teaser text Length")
    public void testTeaserTextLength(String addPath) {

        navigateToStreetCodePage(addPath);

        int teaserParagraphCount = streetCodePage
                .setMainCard()
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
    @Test(dataProvider = "urlProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification that clicking the 'Donate' button displays a modal window with donation options.")
    public void testDonateButtonClick(String addPath) {

        navigateToStreetCodePage(addPath);

        DonateModal donateModal = streetCodePage
                .setQuickDonateButton()
                .getQuickDonateButton()
                .clickDonateButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(donateModal.isFirstTitleDisplayed(), "The title of the modal window is not displayed.'");
        softAssert.assertTrue(donateModal.isAmountInputDisplayed(), "The manual amount input is not displayed.");
        softAssert.assertTrue(donateModal.areAmountButtonsDisplayed(), "The manual amount buttons are not displayed.");
        softAssert.assertTrue(donateModal.isDonateButtonDisplayed(), "The 'Donate' button is not displayed.");

        softAssert.assertAll();
    }
}
