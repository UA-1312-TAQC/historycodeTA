package com.historycode.ui;

import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.page.homePage.HomePage;
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
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Map;

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

        softAssert.assertAll();

    }

    @Issue("91")
    @Test
    @Step("Verify that a red timeline is displayed and a grey square is visible under each year in a timeline.")
    public void testChronologyTimeLineIsDisplayed() {

        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getRedTimeline();

        WebElement redTimeline = chronologyComponent.getRedTimeline();
        softAssert.assertTrue(redTimeline.isDisplayed(), "Red timeline element is not displayed!");
        WebElement years = chronologyComponent.getYears();
        softAssert.assertTrue(years.isDisplayed(), "Years is not visible!");
        WebElement greyBox = chronologyComponent.getGreyBox();
        softAssert.assertTrue(greyBox.isDisplayed(), "Grey box is not visible!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify that the selected year box is bigger than the others.")
    public void testSelectedYearBoxSize() {

        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getRedTimeline();

        ChronologyYearsBarComponent yearsBar = new ChronologyYearsBarComponent(driver);
        yearsBar.sleep(10000);

        yearsBar.clickYearBoxByIndex(3);
        yearsBar.getSelectedYearBoxByIndex(3);
        int targetIndex = 3;

        Assert.assertTrue(yearsBar.isYearBoxLarger(targetIndex),
                "The selected year box is not larger than the others!");
    }

    @Issue("91")
    @Test
    @Step("Verify each event is located separately as an element of a camera film.")
    public void testEachEventIsLocatedSeparately() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);

        for (int i = 4; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
        }

        filmCardComponent.getFilmCardByIndex(0);
        filmCardComponent.isFilmCardProperlySeparated(0);
        for (int i = 0; i <= 16; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
            filmCardComponent.getFilmCardByIndex(i);
            filmCardComponent.isFilmCardProperlySeparated(i);

            Assert.assertTrue(filmCardComponent.isFilmCardProperlySeparated(i), "Film card at index " + i + " is not properly separated!");
        }
    }

    @Issue("91")
    @Test
    @Step(" Verify each event contains: a period of time (date, season), Title, and Main Text.")
    public void testEventsComplete() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();
        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        for (int i = 4; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
        }
        boolean result = true;
        for (int i = 0; i <= 16; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
            filmCardComponent.getFilmCardByIndex(i);
            Map<String, String> card = filmCardComponent.getFilmCardData(i);
            for (Map.Entry<String, String> entry : card.entrySet()) {
                result = result && (entry.getValue() != null) && (entry.getValue().length() > 0);// порівнює та витягує інформацію
                if (!result) {
                    System.out.println("Element is empty : " + entry.getKey());
                }
            }
        }

        Assert.assertTrue(result);
    }

    @Issue("91")
    @Test
    @Step("Verify that clicking on another square leads to a scroll of a camera film to another event in the selected timeline.")
    public void testClickFilmCard() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);
        ChronologyYearsBarComponent chronologyYearsBarComponent = new ChronologyYearsBarComponent(driver);

        filmCardComponent.getFilmCardByIndex(4);
        filmCardComponent.clickFilmCardByIndex(4);
        filmCardComponent.sleep(10000);

        chronologyYearsBarComponent.getRedTimeLine();
        chronologyYearsBarComponent.sleep(10000);

        String activeYearText = chronologyYearsBarComponent.getActiveYearBoxText();
        System.out.println("Active year text after clicking film card: " + activeYearText);

    }

    @Issue("91")
    @Test
    @Step("Verify that no more than 400 symbols (including spaces) are on each card.")
    public void testDescriptionLength() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);
        for (int i = 4; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
        }
        filmCardComponent.getFilmCardByIndex(0);
        filmCardComponent.descriptionsWithinLimit(400);
        for (int i = 0; i <= 16; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
            filmCardComponent.getFilmCardByIndex(i);
            filmCardComponent.descriptionsWithinLimit(400);
        }

        boolean descriptionsValid = filmCardComponent.descriptionsWithinLimit(400);
        Assert.assertTrue(descriptionsValid, "Some film card descriptions exceed 400 characters!");


    }

    @Issue("91")
    @Test
    @Step("Verify images for the background are a set of 3 default images.")
    public void testDefaultBackgroundImages() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);

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
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyYearsBarComponent carouselComponent = new ChronologyYearsBarComponent(driver);
        carouselComponent.verifyCarouselChronology();

    }


    @Issue("91")
    @Test
    @Step(" Verify camera film reacts to a scroll and moves accordingly" +
            "Scroll right moves the camera film to the newest events ->" +
            "Scroll left moves the camera film to the oldest events <-")
    public void testNavigationFilmCard() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getRedTimeline();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        for (int i = 5; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
            WebElement currentCard = filmCardComponent.getFilmCardByIndex(i);
            softAssert.assertTrue(currentCard.isDisplayed(),
                    "Film card at index " + i + " is not visible after scrolling left!");
        }

        WebElement firstCard = filmCardComponent.getFilmCardByIndex(0);
        softAssert.assertTrue(firstCard.isDisplayed(),
                "First film card (index 0) is not visible after scrolling left!");

        for (int i = 0; i <= 16; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
            WebElement currentCard = filmCardComponent.getFilmCardByIndex(i);
            softAssert.assertTrue(currentCard.isDisplayed(),
                    "Film card at index " + i + " is not visible after scrolling right!");
        }

        WebElement lastCard = filmCardComponent.getFilmCardByIndex(16);
        softAssert.assertTrue(lastCard.isDisplayed(),
                "Last film card (index 16) is not visible after scrolling right!");

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
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);
        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);


        for (int i = 4; i >= 0; i--) {
            filmCardComponent.clickFilmCardByIndex(i);
        }
        boolean isSorted = true;
        for (int i = 0; i <= 15; i++) {
            filmCardComponent.clickFilmCardByIndex(i);
            filmCardComponent.getFilmCardByIndex(i);
            isSorted = isSorted && filmCardComponent.eventsChronologySorted();

        }

        Assert.assertTrue(isSorted, "Events are not displayed in chronological order!");
    }


    @Issue("91")
    @Test
    @Step("Сlicking on previous/next event - moves events cards and locates clicked one to the center.")
    public void testEventMovesToCenter() {

        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
        filmCardComponent.sleep(10000);

        int targetIndex = 5;

        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);

        softAssert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");

        filmCardComponent.clickFilmCardByIndex(targetIndex);

        boolean isCentered = filmCardComponent.isCardCentered(targetIndex);

        softAssert.assertTrue(isCentered, "Card at index " + targetIndex + " is not centered!");

        softAssert.assertAll();
    }

    @Issue("91")
    @Test
    @Step("Verify the central event has a white outline.")
    public void testCentralEventHasSpecificBorderColor() {
        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        BurgerMenuComponent burgerMenuComponent = homePage.getBurgerMenuComponent();
        burgerMenuComponent.clickMenuItem("History-коди");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        streetCodesPage.clickOnCatalogComponent(0);

        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
        chronologyComponent.getFilmCardContainer();

        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);

        int targetIndex = 5;
        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
        softAssert.assertTrue(filmCardByIndex.isDisplayed(),
                "Film card at index " + targetIndex + " is not visible!");
        filmCardComponent.clickFilmCardByIndex(targetIndex);

        String expectedBorderColor = "#d9d9d9";
        boolean hasCorrectBorder = filmCardComponent.borderColor(targetIndex, expectedBorderColor);
        softAssert.assertTrue(hasCorrectBorder,
                "The film card at index " + targetIndex + " does not have the correct border color!");

        for (int i = 0; i < filmCardComponent.getFilmCard().size(); i++) {
            softAssert.assertTrue(filmCardComponent.borderColor(i, expectedBorderColor),
                    "The film card at index " + i + " incorrectly has the expected border color!");

            softAssert.assertAll();
        }
    }
}








