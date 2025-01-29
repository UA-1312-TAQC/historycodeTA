package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyFilmCardComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyYearsBarComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class ChronologyTestCase extends BaseTestRunner {

    SoftAssert softAssert;
    private static final Logger logger = LoggerFactory.getLogger(ChronologyTestCase.class);
    protected StreetCodePage streetCodePage;

    @BeforeMethod
    public void openStreetCodePage() {
        streetCodePage = new HomePage(driver)
                .openBurgerMenu()
                .goToStreetCodeCatalogPage()
                .clickCatalogItemByIndex(0);
        softAssert = new SoftAssert();
    }

    @Issue("91")
    @Test
    @Step("Verify the title 'Хронологія' is displayed.")
    public void testChronologyDisplayed() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        WebElement titleElement = chronologyComponent.getTitleElement();
        softAssert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");

        String actualTitle = chronologyComponent.getTitle();
        softAssert.assertEquals(actualTitle, "Хронологія", "The title text is incorrect!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that a red timeline is displayed and a grey square is visible under each year in a timeline.")
    public void testChronologyTimelineIsDisplayed() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        WebElement redTimeline = chronologyComponent.getRedTimeline();
        softAssert.assertTrue(redTimeline.isDisplayed(), "Red timeline element is not displayed!");

        WebElement years = chronologyComponent.getYear();
        softAssert.assertTrue(years.isDisplayed(), "Years element is not visible!");

        WebElement greyBox = chronologyComponent.getGreyBox();
        softAssert.assertTrue(greyBox.isDisplayed(), "Grey box is not visible!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that the selected year box is bigger than the others.")
    public void testSelectedYearBoxSize() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyYearsBarComponent yearsBar = chronologyComponent.getYearsBar();

        chronologyComponent.clickYearBoxByIndex(3);
        boolean isLarger = yearsBar.isYearBoxLarger();

        Assert.assertTrue(isLarger, "The selected year box is not larger than the others!");
    }

    @Issue("91")
    @Test
    @Step("Verify each event is located separately as an element of a camera film.")
    public void testEachEventIsLocatedSeparately() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();


        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickFilmCardByIndex(i);
            Map<String, String> filmCardData = filmCardComponent.getFilmCardData();
            logger.info("Film card data at index {}: {}", i, filmCardData);
            boolean isSeparated = filmCardComponent.isFilmCardSeparated();
            softAssert.assertTrue(isSeparated, "Film card at index " + i + " is not properly separated!");
        }

        for (int i = 0; i <= 16; i++) {
            chronologyComponent.clickFilmCardByIndex(i);
            Map<String, String> filmCardData = filmCardComponent.getFilmCardData();
            logger.info("Film card data at index {}: {}", i, filmCardData);
            boolean isSeparated = filmCardComponent.isFilmCardSeparated();
            softAssert.assertTrue(isSeparated, "Film card at index " + i + " is not properly separated!");
        }

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify each event contains: a period of time (date, season), Title, and Main Text.")
    public void testEventsComplete() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickFilmCardByIndex(i);
        }
        boolean result = true;
        for (int i = 0; i <= 16; i++) {
            chronologyComponent.clickFilmCardByIndex(i);
            chronologyComponent.getFilmCardByIndex(i);
            Map<String, String> card = filmCardComponent.getFilmCardData();
            for (Map.Entry<String, String> entry : card.entrySet()) {
                result = result && (entry.getValue() != null) && (entry.getValue().length() > 0);// порівнює та витягує інформацію
                if (!result) {
                    System.out.println("Element is empty : " + entry.getKey());
                }
            }
        }
    }

    @Issue("91")
    @Test
    @Step("Verify that clicking on another square leads to a scroll of a camera film to another event in the selected timeline.")
    public void testClickFilmCard() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyYearsBarComponent yearsBar = chronologyComponent.getYearsBar();

        chronologyComponent.clickFilmCardByIndex(3);
        yearsBar.getRedTimeline();
        yearsBar.getActiveYearBoxText();

        softAssert.assertTrue(yearsBar.getRedTimeline().isDisplayed(), "Red timeline is not displayed!");
        System.out.println("Active year text: " + yearsBar.getActiveYearBoxText());

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that no more than 400 symbols (including spaces) are on each card.")
    public void testDescriptionLength() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickFilmCardByIndex(i);
        }
        chronologyComponent.getFilmCardByIndex(0);
        filmCardComponent.descriptionsWithinLimit(400);
        for (int i = 0; i <= 16; i++) {
            chronologyComponent.clickFilmCardByIndex(i);
            chronologyComponent.getFilmCardByIndex(i);
            filmCardComponent.descriptionsWithinLimit(400);
        }

        boolean descriptionsValid = filmCardComponent.descriptionsWithinLimit(400);
        Assert.assertTrue(descriptionsValid, "Some film card descriptions exceed 400 characters!");
    }

    @Issue("91")
    @Test
    @Step("Verify images for the background are a set of 3 default images.")
    public void testDefaultBackgroundImages() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        List<String> actualImageUrls = filmCardComponent.getBackgroundImageUrls();
        List<String> defaultImageUrls = List.of(
                "https://frontend.historycode.online/assets/6e65d6e008ddb4e343bd.webp",
                "https://frontend.historycode.online/assets/3a1f24a900dfca1fed4e.webp",
                "https://frontend.historycode.online/assets/6e65d6e008ddb4e343bd.webp"
        );

        for (String actualUrl : actualImageUrls) {
            Assert.assertTrue(defaultImageUrls.contains(actualUrl),
                    "Background image URL not part of the default set: " + actualUrl);
        }
    }

    @Issue("91")
    @Test
    @Step("Verify events are displayed from oldest to newest, from left to right.")
    public void testEventsChronologySortedWithoutConversion() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyYearsBarComponent yearsBarComponent = chronologyComponent.getYearsBar();

        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickYearBoxByIndex(i);
        }

        chronologyComponent.getYearNodeByIndex(0);
        yearsBarComponent.yearsChronologicallySorted();
        for (int i = 0; i <= 8; i++) {
            chronologyComponent.clickYearBoxByIndex(i);
            chronologyComponent.getYearNodeByIndex(i);
            yearsBarComponent.yearsChronologicallySorted();

            boolean isSorted = yearsBarComponent.yearsChronologicallySorted();

            Assert.assertTrue(isSorted, "Events are not displayed in chronological order!");
        }
    }

    @Issue("91")
    @Test
    @Step(" Verify camera film reacts to a scroll and moves accordingly" +
            "Scroll right moves the camera film to the newest events ->" +
            "Scroll left moves the camera film to the oldest events <-")
    public void testNavigationFilmCard() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickFilmCardByIndex(i);
            chronologyComponent.getFilmCardByIndex(i);
        }

        chronologyComponent.getFilmCardByIndex(0);
        for (int i = 0; i <= 16; i++) {
            chronologyComponent.clickFilmCardByIndex(i);
            chronologyComponent.getFilmCardByIndex(i);
        }
        chronologyComponent.getFilmCardByIndex(16);

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify the ordering of events:\n" +
            "\n" +
            "the beginning of the year is considered as the 1st of January\n" +
            "\n" +
            "the beginning of the season is considered the first day of its first month,\n" +
            "\n" +
            "the beginning of the month is considered the first day of this month")
    public void testTimelineNavigationAndSorting() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        for (int i = 4; i >= 0; i--) {
            chronologyComponent.clickFilmCardByIndex(i);
        }
        boolean isSorted = true;
        for (int i = 0; i <= 15; i++) {
            chronologyComponent.clickFilmCardByIndex(i);
            chronologyComponent.getFilmCardByIndex(i);
            isSorted = isSorted && filmCardComponent.eventsChronologySorted();
        }

        Assert.assertTrue(isSorted, "Events are not displayed in chronological order!");
    }

    @Issue("91")
    @Test
    @Step("Сlicking on previous/next event - moves events cards and locates clicked one to the center.")
    public void testEventMovesToCenter() {

        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        int targetIndex = 5;
        chronologyComponent.getFilmCardByIndex(targetIndex);
        chronologyComponent.clickFilmCardByIndex(targetIndex);

        boolean isCentered = filmCardComponent.isCardCentered();
        softAssert.assertTrue(isCentered, "Card at index " + targetIndex + " is not centered!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify the central event has a white outline.")
    public void testCentralEventHasSpecificBorderColor() {
        ChronologyComponent chronologyComponent = streetCodePage.getTimeline();
        ChronologyFilmCardComponent filmCardComponent = chronologyComponent.getFilmCardComponent();

        int targetIndex = 5;
        chronologyComponent.getFilmCardByIndex(targetIndex);
        chronologyComponent.clickFilmCardByIndex(targetIndex);

        String expectedBorderColor = "#d9d9d9";
        boolean hasCorrectBorder = filmCardComponent.borderColor(expectedBorderColor);
        softAssert.assertTrue(hasCorrectBorder,
                "The film card at index " + targetIndex + " does not have the correct border color!");

            softAssert.assertAll();
        }
    }










