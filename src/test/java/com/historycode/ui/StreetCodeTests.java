package com.historycode.ui;

import com.historycode.ui.data_provider.StreetCodeDP;
import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StreetCodeTests extends BaseTestRunner {
    private static final int MAX_ALLOWED_TEASER_PARAGRAPHS = 2;
    private static final int MAX_SINGLE_TEASER_PARAGRAPH_LENGTH = 520;
    private static final int MAX_TWO_TEASER_PARAGRAPH_LENGTH = 455;

    private StreetCodeCatalogPage streetCodeCatalogPage;
    private StreetCodePage streetCodePage;

    @BeforeMethod
    @Step("Navigate to the 'StreetCodeCatalog' page")
    private void navigateToStreetCodeCatalogPage() {
        streetCodeCatalogPage = new HomePage(driver)
                .openBurgerMenu()
                .goToStreetCodeCatalogPage();
    }

    @Step("Open the 'StreetCode' page")
    private void openStreetCodePage(int index) {
        streetCodePage = streetCodeCatalogPage
                .clickCatalogItemByIndex(index);
    }

    @Issue("73")
    @Test(dataProvider = "indexTeaserSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification of the teaser text Length")
    public void testTeaserTextLength(int index) {

        openStreetCodePage(index);

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
                    ("The 'Teaser' text is too long for one paragraph."));
        } else if (teaserParagraphCount == 2) {
            Assert.assertTrue(teaserCharacterCount <= MAX_TWO_TEASER_PARAGRAPH_LENGTH,
                    ("The 'Teaser' text is too long for two paragraphs."));
        }

        Assert.assertFalse(streetCodePage.getMainCard().isTeaserTextOverflowing(),
                ("The 'Teaser' text is displayed with truncation or overflow."));
    }

    @Issue("79")
    @Test(priority = 1)
    @Description("Verification that clicking the 'Donate' button displays a modal window with donation options")
    public void testDonateButtonClick() {

        openStreetCodePage(0);

        DonateModal donateModal = streetCodePage
                .getQuickDonateButton()
                .clickDonateButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(donateModal.isFirstTitleDisplayed(), "The title of the modal window is not displayed.");
        softAssert.assertTrue(donateModal.isAmountInputDisplayed(), "The manual amount input is not displayed.");
        softAssert.assertTrue(donateModal.areAmountButtonsDisplayed(), "The amount buttons are not displayed.");
        softAssert.assertTrue(donateModal.isAgreeCheckboxDisplayed(), "The 'Agree' checkbox is not displayed.");
        softAssert.assertTrue(donateModal.isDonateButtonDisplayed(), "The 'Donate' button is not displayed.");

        softAssert.assertAll();
    }

    @Issue("80")
    @Test(priority = 1)
    @Description("Verification if working 'Трохи ще' button and 'Дещо менше' if there is more text available on the page")
    public void testCheckExpandButton() {

        openStreetCodePage(0);

        boolean isReadMoreDisplayed = streetCodePage
                .scrollToTextVideoBlock()
                .getTextBlock()
                .isReadMoreButtonDisplayed();

        boolean isTextFitsOnOneScreen = streetCodePage
                .getTextBlock()
                .isTextFitsOneScreen();

        Assert.assertTrue(isReadMoreDisplayed, "The 'Read More' button is not displayed.");
        Assert.assertTrue(isTextFitsOnOneScreen, "The text is not displayed on one screen.");

        int paragraphFirstCount = streetCodePage
                .getTextBlock()
                .getParagraphCount();

        streetCodePage
                .getTextBlock()
                .clickReadMoreButton();

        boolean isLessButtonDisplayed = streetCodePage
                .getTextBlock()
                .isReadLessButtonDisplayed();

        boolean checkExpanded = streetCodePage
                .getTextBlock()
                .checkExpanded(paragraphFirstCount);

        Assert.assertTrue(checkExpanded, "The text is not expanded.");
        Assert.assertTrue(isLessButtonDisplayed, "The less button is not displayed.");

        boolean checkCollapsed = streetCodePage
                .getTextBlock()
                .clickReadLessButton()
                .checkCollapsed(paragraphFirstCount);

        isReadMoreDisplayed = streetCodePage
                .getTextBlock()
                .isReadMoreButtonDisplayed();

        Assert.assertTrue(checkCollapsed, "The text is not collapsed.");
        Assert.assertTrue(isReadMoreDisplayed, "The expand button is not displayed.");
    }

    @Issue("86")
    @Test(dataProvider = "urlOneWowFactSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification if only one fact is displayed - it is located in the center of the block")
    public void testWowFactsOneElementAlign(int index) {

        openStreetCodePage(index);

        boolean isOneCard = streetCodePage
                .scrollToInterestingFacts()
                .getFacts()
                .getCarousel()
                .isOneCardPresent();

        Assert.assertTrue(isOneCard, "The carousel contains more than one card.");

        SoftAssert softAssert = new SoftAssert();

        boolean isCardInCenterOfBlock = streetCodePage
                .getFacts()
                .getCarousel()
                .isCardInCenterOfBlock();

        softAssert.assertTrue(isCardInCenterOfBlock);

        boolean hasArrows = streetCodePage
                .getFacts()
                .getCarousel()
                .hasArrows();

        softAssert.assertFalse(hasArrows, "The navigation arrows are displayed.");

        boolean hasPagination = streetCodePage
                .getFacts()
                .getCarousel()
                .hasPagination();

        softAssert.assertFalse(hasPagination, "Pagination is displayed.");

        softAssert.assertAll();
    }

    @Issue("87")
    @Test(dataProvider = "urlWowFactSetProvider", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verification that if 3 or more facts are displayed, they scroll in a loop")
    public void testWowFactsScroll(int index) {
        final int ADDITIONAL_CARD = 2;

        openStreetCodePage(index);

        int countFactCard = streetCodePage
                .scrollToInterestingFacts()
                .getFacts()
                .getCarousel()
                .getCardCount();

        Assert.assertTrue(countFactCard >= (3 + ADDITIONAL_CARD), "The carousel contains less than 3 cards.");

        String currentCardTitle = streetCodePage
                .getFacts()
                .getCarousel()
                .getCurrentNodeTitle();

        streetCodePage
                .getFacts()
                .getCarousel()
                .dynamicClickNextButton(countFactCard - ADDITIONAL_CARD);

        String afterScrollCurrentCardTitle = streetCodePage
                .getFacts()
                .getCarousel()
                .getCurrentNodeTitle();

        Assert.assertEquals(currentCardTitle, afterScrollCurrentCardTitle,
                "The carousel is not scrolling in a loop.");
    }

}
