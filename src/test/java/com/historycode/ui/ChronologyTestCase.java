package com.historycode.ui;

import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyFilmCardComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyYearsBarComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ChronologyTestCase extends BaseTestRunner {

    SoftAssert softAssert = new SoftAssert();
    private static final Logger logger = LoggerFactory.getLogger(ChronologyTestCase.class);

    @Issue("91")
    @Test
    @Step("Verify the title 'Хронологія' is displayed.")
    public void testChronologyDisplaeyd() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);

        WebElement titleElement = chronologyComponent.getTitleElement();
        softAssert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");

        String actualTitle = chronologyComponent.getTitle();
        softAssert.assertEquals(actualTitle, "Хронологія", "The title text is incorrect!");

    }

    @Issue("91")
    @Test
    @Step("Verify that a red timeline is displayed and a grey square is visible under each year in a timeline.")
    public void testChronologyTimeLineIsDisplayed() {

        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);

        WebElement redTimeline = chronologyComponent.getRedTimeline();
        softAssert.assertTrue(redTimeline.isDisplayed(), "Red timeline element is not displayed!");
        WebElement years = chronologyComponent.getYears();
        softAssert.assertTrue(years.isDisplayed(), "Years is not visible!");
        WebElement greyBox = chronologyComponent.getGreyBox();
        softAssert.assertTrue(greyBox.isDisplayed(), "Grey box is not visible!");
    }

    @Issue("91")
    @Test
    @Step("Verify that the selected year box is bigger than the others.")
    public void testSelectedYearBoxSize() {

        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyYearsBarComponent yearsBar = new ChronologyYearsBarComponent(driver);
        WebElement redTimeline = yearsBar.getRedTimeLine();
        softAssert.assertTrue(redTimeline.isDisplayed(), "Red timeline element is not displayed!");

        int targetIndex = 4;

        softAssert.assertTrue(yearsBar.isYearBoxLarger(targetIndex),
                "The selected year box is not larger than the others!");
    }

    @Issue("91")
    @Test
    @Step("Verify that clicking on another square leads to a scroll of a camera film to another event in the selected timeline.")
    public void testClickFilmCard() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 6;
        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        Assert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);
    }

    @Issue("91")
    @Test
    @Step("Verify each event is located separately as an element of a camera film.")
    public void testEachEventIsLocatedSeparately() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int filmCardCount = filmCardComponent.getFilmCardCount();
        softAssert.assertTrue(filmCardCount > 0, "No film cards found on the timeline!");

        softAssert.assertTrue(filmCardComponent.allFilmCardsVisible(),
                "Not all film cards are visible!");

        softAssert.assertTrue(filmCardComponent.filmCardsUnique(),
                "Some film cards are not unique!");
    }

    @Issue("91")
    @Test
    @Step("Verify each event contains: a period of time (date, season), Title, and Main Text.")
    public void testAllEventCardsComplete() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        for (int i = 0; i < filmCardComponent.getFilmCard().size(); i++) {
            boolean isComplete = filmCardComponent.isCardComplete(i);
            Assert.assertTrue(isComplete, "Event card at index " + i + " is incomplete!");
        }
    }

    @Issue("91")
    @Test
    @Step("Verify that no more than 400 symbols (including spaces) are on each card.")
    public void testDescriptionLength() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        boolean areDescriptionsValid = filmCardComponent.descriptionsWithinLimit(400);
        softAssert.assertTrue(areDescriptionsValid, "Some film card descriptions exceed 400 characters!");

        List<Integer> descriptionLengths = filmCardComponent.getDescriptionLengths();
        logger.info("Description lengths {}: ", descriptionLengths);

        for (int i = 0; i < descriptionLengths.size(); i++) {
            softAssert.assertTrue(descriptionLengths.get(i) <= 400,
                    "Description on card " + i + " exceeds 400 characters!");
        }
    }

    @Issue("91")
    @Test
    @Step("Verify images for the background are a set of 3 default images.")
    public void testDefaultBackgroundImages() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

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
    @Step("Verify events are displayed from oldest to newest, from left to right without converting to Integer.")
    public void testEventsChronologySortedWithoutConversion() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyYearsBarComponent chronologyYearsBarComponent = new ChronologyYearsBarComponent(driver);

        boolean isSorted = chronologyYearsBarComponent.eventsChronologicallySorted();
        Assert.assertTrue(isSorted, "Events are not displayed from oldest to newest!");

    }

    @Issue("91")
    @Test
    @Step(" Verify camera film reacts to a scroll and moves accordingly" +
            "Scroll right moves the camera film to the newest events ->" +
            "Scroll left moves the camera film to the oldest events <-")
    public void testNavigationYearBoxes() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 6;
        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        softAssert.assertFalse(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);

        String filmTitle = "Перемога в суді";
        WebElement filmCardByName = filmCardComponent.getFilmCardByName(filmTitle);
        softAssert.assertFalse(filmCardByName.isDisplayed(),
                "Film card with title '" + filmTitle + "' is not visible!");
        filmCardComponent.clickFilmCardByName(filmTitle);

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
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        for (int i = 4; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
        }

        for (int i = 0; i <= 16; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
        }

        boolean isSorted = filmCardComponent.eventsChronologicallySorted();
        Assert.assertTrue(isSorted, "Events are not displayed in chronological order!");
    }

    @Issue("91")
    @Test
    @Step("Сlicking on previous/next event - moves events cards and locates clicked one to the center.")
    public void testEventMovesToCenter() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 6;

        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        softAssert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");

        filmCardComponent.clickFilmCardByIndex(targetIndex);

        softAssert.assertTrue(filmCardComponent.isCardCentered(targetIndex),
                "The film card at index " + targetIndex + " is not centered after clicking!");
    }

    @Issue("91")
    @Test
    @Step("Verify the central event has a white outline.")
    public void testCentralEventHasSpecificBorderColor() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 6;
        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        softAssert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);

        String expectedBorderColor = "#d9d9d9";
        boolean hasCorrectBorder = filmCardComponent.borderColor(targetIndex, expectedBorderColor);
        softAssert.assertFalse(hasCorrectBorder,
                "The film card at index " + targetIndex + " does not have the correct border color!");

        for (int i = 0; i < filmCardComponent.getFilmCard().size(); i++) {
            softAssert.assertFalse(filmCardComponent.borderColor(i, expectedBorderColor),
                    "The film card at index " + i + " incorrectly has the expected border color!");
        }
    }
}








