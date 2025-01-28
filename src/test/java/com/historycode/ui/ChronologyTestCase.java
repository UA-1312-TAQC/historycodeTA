package com.historycode.ui;


import com.historycode.ui.page.streetCodePage.components.ChronologyComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyFilmCardComponent;
import com.historycode.ui.page.streetCodePage.components.ChronologyYearsBarComponent;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ChronologyTestCase extends BaseTestRunner {


//    @Issue("91")
//    @Test
//    @Step("Verify the title 'Хронологія' is displayed.")
//    public void testChronologyDisplaeyd() {
//        String baseUrl = testValueProvider.getBaseUIUrl();
//        String fullUrl = baseUrl + "catalog";
//        driver.get(fullUrl);
//
//        StreetCodeCatalogPage historyCodePage = new StreetCodeCatalogPage(driver);
//        historyCodePage.clickOnCatalogComponent(0);
//
//        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
//        chronologyComponent.getTitle();
//
//        WebElement titleElement = chronologyComponent.getTitleElement();
//        Assert.assertTrue(titleElement.isDisplayed(), "Title element is not visible!");
//    }

//    @Issue("91")
//    @Test
//    @Step("Verify that a red timeline is displayed and a grey square is visible under each year in a timeline.")
//    public void testChronologyTimeLineIsDisplayed() {
//
//        String baseUrl = testValueProvider.getBaseUIUrl();
//        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
//        driver.get(fullUrl);
//
//        ChronologyComponent chronologyComponent = new ChronologyComponent(driver);
//        chronologyComponent.getRedTimeline();
//
//        ChronologyYearsBarComponent chronologyYearsBarComponent = new ChronologyYearsBarComponent(driver);
//
//        int targetIndex = 0;
//
//        Assert.assertTrue(chronologyYearsBarComponent.isYearBoxVisible(targetIndex),
//                "The year box at index " + targetIndex + " is not visible!");
//    }

//    @Issue("91")
//    @Test
//    @Step("Verify that the selected year box is larger than the others.")
//    public void testSelectedYearBoxSize() {
//
//        String baseUrl = testValueProvider.getBaseUIUrl();
//        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
//        driver.get(fullUrl);
//
//        ChronologyYearsBarComponent yearsBar = new ChronologyYearsBarComponent(driver);
//
//        int targetIndex = 3;
//
//        Assert.assertFalse(yearsBar.isYearBoxLarger(targetIndex),
//                "The selected year box is not larger than the others!");
//    }
//
//    @Issue("91")
//    @Test
//    @Step("Verify that clicking on another square leads to a scroll of a camera film to another event in the selected timeline.")
//    public void testNavigateToCameraFilm() {
//
//        String baseUrl = testValueProvider.getBaseUIUrl();
//        String fullUrl = baseUrl + "roman-ratushnyi-seneka";
//        driver.get(fullUrl);
//
//        ChronologyFilmCardComponent filmCardComponent = new ChronologyFilmCardComponent(driver);
//
//        int targetIndex = 6;
//        WebElement filmCardByIndex = filmCardComponent.getFilmCardByIndex(targetIndex);
//        Assert.assertTrue(filmCardByIndex.isDisplayed(), "Film card at index " + targetIndex + " is not visible!");
//        filmCardComponent.clickFilmCardByIndex(targetIndex);
//
//        String filmTitle = "Перемога в суді";
//        WebElement filmCardByName = filmCardComponent.getFilmCardByName(filmTitle);
//        Assert.assertTrue(filmCardByName.isDisplayed(), "Film card with title '" + filmTitle + "' is not visible!");
//        filmCardComponent.clickFilmCardByName(filmTitle);
//    }
}




