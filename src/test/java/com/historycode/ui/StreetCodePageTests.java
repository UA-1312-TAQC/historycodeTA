package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.MainCardComponent;
import com.historycode.ui.page.streetCodePage.components.TagsPersonasCardComponent;
import com.historycode.ui.page.streetCodePage.modals.KeywordPersonasModal;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StreetCodePageTests extends BaseTestRunner {
    private StreetCodePage streetCodePage;
    private SoftAssert softAssert;

    private MainCardComponent mainCard;

    @BeforeMethod
    @Step("Navigate to StreetCode page")
    public void navigateToStreetCodePage() {
        softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        homePage.getBurgerMenuComponent().clickMenuItem("History-коди");

        StreetCodeCatalogPage catalogPage = new StreetCodeCatalogPage(driver);
        streetCodePage = catalogPage.clickCatalogItemByIndex(0);
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

        //Verify the visibility of the Donate button when scrolling
        Point initialPosition = streetCodePage.getQuickDonateButton().getButtonLocation();
        streetCodePage.getQuickDonateButton().scrollToMiddlePage();
        Point middlePosition = streetCodePage.getQuickDonateButton().getButtonLocation();

        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(),
                "Donate button should be visible after scrolling to middle");
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isClickable(),
                "Donate button should be clickable after scrolling to middle");
        softAssert.assertEquals(initialPosition.getX(), middlePosition.getX(),
                "X-coordinate of Donate button should remain the same after scrolling to middle");
        softAssert.assertTrue(middlePosition.getY() > initialPosition.getY(),
                "Y-coordinate of Donate button should increase after scrolling to middle");

        streetCodePage.getQuickDonateButton().scrollToEndOfPage();
        Point endPosition = streetCodePage.getQuickDonateButton().getButtonLocation();

        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isDonateButtonDisplayed(),
                "Donate button should be visible after scrolling to bottom");
        softAssert.assertTrue(streetCodePage.getQuickDonateButton().isClickable(),
                "Donate button should be clickable after scrolling to bottom");
        softAssert.assertEquals(initialPosition.getX(), endPosition.getX(),
                "X-coordinate of Donate button should remain the same after scrolling to bottom");
        softAssert.assertTrue(endPosition.getY() > middlePosition.getY(),
                "Y-coordinate of Donate button should increase after scrolling to bottom");

        softAssert.assertAll();
    }

    @Issue("77")
    @Test
    @Description("Verify that clicking on the tag opens a modal window with relevant StreetCodes")
    public void testTagModalFunctionality() {

        mainCard = streetCodePage.getMainCard();
        softAssert.assertFalse(mainCard.getTags().isEmpty(), "Tags should be present on the page");
        String selectedTag = mainCard.getTags().get(0);

        //test tag hover
        String originalBorderColor = mainCard.getTagBorderColor(selectedTag);
        String originalTextColor = mainCard.getTagTextColor(selectedTag);

        mainCard.hoverOverTag(selectedTag);

        String hoveredBorderColor = mainCard.getTagBorderColor(selectedTag);
        String hoveredTextColor = mainCard.getTagTextColor(selectedTag);

        softAssert.assertNotEquals(hoveredBorderColor, originalBorderColor,
                "Tag border color should change on hover");
        softAssert.assertNotEquals(hoveredTextColor, originalTextColor,
                "Tag text color should change on hover");

        //test modal
        KeywordPersonasModal modal = mainCard.clickKeyword(selectedTag);
        softAssert.assertTrue(mainCard.isModalVisible(),
                "Modal window should be visible after clicking the tag");
        softAssert.assertFalse(modal.getPersons().isEmpty(),
                "Modal should contain related StreetCode cards");

        TagsPersonasCardComponent firstPerson = modal.getPersons().get(0);
        softAssert.assertNotNull(firstPerson.getName(),
                "Cards in modal should have a displayed name");

        //verify selected tag presence
        String selectedTagInModal = modal.getSelectedTag();
        softAssert.assertEquals(selectedTagInModal, selectedTag,
                "Selected tag should be highlighted in modal");

        softAssert.assertAll();
    }
}
