package com.historycode.ui;

import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyFilmCardComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyYearsBarComponent;
import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ChronologyTestCase2 extends BaseTestRunner {

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
        Assert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");
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
        Assert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");

        String actualTitle = chronologyComponent.getTitle();
        Assert.assertEquals(actualTitle, "Хронологія", "The title text is incorrect!");
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

        Assert.assertFalse(yearsBar.isYearBoxLarger(targetIndex),
                "The selected year box is not larger than the others!");
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
        Assert.assertTrue(filmCardByIndex.isDisplayed(), "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);

        String filmTitle = "Перемога в суді";
        WebElement filmCardByName = filmCardComponent.getFilmCardByName(filmTitle);
        Assert.assertTrue(filmCardByName.isDisplayed(), "Film card with title '" + filmTitle + "' is not visible!");
        filmCardComponent.clickFilmCardByName(filmTitle);
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
        Assert.assertTrue(filmCardCount > 0, "No film cards found on the timeline!");

        Assert.assertFalse(filmCardComponent.areAllFilmCardsVisible(),
                "Not all film cards are visible!");

        Assert.assertTrue(filmCardComponent.areFilmCardsUnique(),
                "Some film cards are not unique!");
    }

    @Issue("91")
    @Test
    @Step("Verify each event contains: a period of time (date, season), Title, and Main Text.")
    public void testEachEventContainsRequiredFields() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        Assert.assertTrue(filmCardComponent.getFilmCards().size() > 0, "No film cards found on the timeline!");

        Assert.assertFalse(filmCardComponent.areAllEventsComplete(),
                "Some film cards do not contain all required fields (Year, Historical Context, Title, or Description)!");
    }

    @Issue("91")
    @Test
    @Step("Log event details for each film card.")
    public void testLogEventDetails() {
        String baseUrl = testValueProvider.getBaseUIUrl();
        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
        driver.get(fullUrl);

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int numberOfEvents = filmCardComponent.getFilmCards().size();
//        System.out.println("Total Events Found: " + numberOfEvents);
        logger.info("Total Events Found: {}", numberOfEvents);

        for (int i = 0; i < numberOfEvents; i++) {
//            System.out.println("Event " + (i + 1) + ": " + filmCardComponent.getEventDetails(i));
            String eventDetails = filmCardComponent.getEventDetails(i);
            logger.info("Event {}: {}", (i + 1), eventDetails);
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

        boolean areDescriptionsValid = filmCardComponent.areDescriptionsWithinLimit(400);
        Assert.assertTrue(areDescriptionsValid, "Some film card descriptions exceed 400 characters!");

        List<Integer> descriptionLengths = filmCardComponent.getDescriptionLengths();
//        System.out.println("Description lengths: " + descriptionLengths);
        logger.info("Description lengths {}: ", descriptionLengths);

        for (int i = 0; i < descriptionLengths.size(); i++) {
            Assert.assertTrue(descriptionLengths.get(i) <= 400,
                    "Description on card " + i + " exceeds 400 characters!");
        }
    }
}






