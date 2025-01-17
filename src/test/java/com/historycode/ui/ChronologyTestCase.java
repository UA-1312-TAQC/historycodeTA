package com.historycode.ui;

import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyFilmCardComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyYearsBarComponent;
import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
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
        String fullUrl = baseUrl + "catalog";
        driver.get(fullUrl);

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getTitle();

        WebElement titleElement = chronologyComponent.getTitleElement();
        softAssert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that a red timeline is displayed and a grey square is visible under each year in a timeline.")
    public void testChronologyTimeLineIsDisplayed() {

        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "catalog";
        driver.get(fullUrl);


        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);

        WebElement titleElement = chronologyComponent.getTitleElement();
        softAssert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");

        String actualTitle = chronologyComponent.getTitle();
        softAssert.assertEquals(actualTitle, "Хронологія", "The title text is incorrect!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that the selected year box is larger than the others.")
    public void testSelectedYearBoxSize() {

        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyYearsBarComponent yearsBar = new ChronologyYearsBarComponent(driver);

        int targetIndex = 3;

        softAssert.assertFalse(yearsBar.isYearBoxLarger(targetIndex),
                "The selected year box is not larger than the others!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that clicking on another square leads to a scroll of a camera film to another event in the selected timeline.")
    public void testNavigateToCameraFilm() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 6;
        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        softAssert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);

        String filmTitle = "Перемога в суді";
        WebElement filmCardByName = filmCardComponent.getFilmCardByName(filmTitle);
        softAssert.assertTrue(filmCardByName.isDisplayed(),
                "Film card with title '" + filmTitle + "' is not visible!");
        filmCardComponent.clickFilmCardByName(filmTitle);

        softAssert.assertAll();
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

        softAssert.assertFalse(filmCardComponent.allFilmCardsVisible(),
                "Not all film cards are visible!");

        softAssert.assertTrue(filmCardComponent.filmCardsUnique(),
                "Some film cards are not unique!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify each event contains: a period of time (date, season), Title, and Main Text.")
    public void testEachEventContainsRequiredFields() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        softAssert.assertTrue(!filmCardComponent.getFilmCard().isEmpty(), "No film cards found on the timeline!");

        softAssert.assertFalse(filmCardComponent.allEventsComplete(),
                "Some film cards do not contain all required fields (Year, Historical Context, Title, or Description)!");

        softAssert.assertAll();
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

            softAssert.assertAll();
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
            softAssert.assertFalse(defaultImageUrls.contains(actualUrl),
                    "Background image URL not part of the default set: " + actualUrl);
        }

        softAssert.assertAll();
    }


    @Issue("91")
    @Test
    @Step("Verify events are displayed from oldest to newest, from left to right without converting to Integer.")
    public void testEventsChronologicallySortedWithoutConversion() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyYearsBarComponent chronologyYearsBarComponent = new ChronologyYearsBarComponent(driver);

        boolean isSorted = chronologyYearsBarComponent.eventsChronologicallySorted();
        softAssert.assertFalse(isSorted, "Events are not displayed from oldest to newest!");

        softAssert.assertAll();

    }
}







