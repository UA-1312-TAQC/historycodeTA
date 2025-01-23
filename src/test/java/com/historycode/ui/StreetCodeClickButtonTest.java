package com.historycode.ui;

import com.historycode.ui.data_provider.StreetCodeDP;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.StreetCodeTextBlockComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;



public class StreetCodeClickButtonTest extends BaseTestRunner {
    private StreetCodePage streetCodePage;

    @Step("Navigate to the 'StreetCode' page")
    private void navigateToStreetCodePage(String addUIUrl) {
        driver.navigate().to(testValueProvider.getBaseUIUrl() + addUIUrl);
        streetCodePage = new StreetCodePage(driver);
    }

    @Issue("81")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verify that buttons are clickable and text expand/collapse")
    public void testStreetCodeClick(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);

        StreetCodeTextBlockComponent textBlock = streetCodePage.getTextBlock();
        int initialCount = textBlock.getParagraphCount();
        SoftAssert softAssert = new SoftAssert();

        //step : click on button 'Трохи ще'
        softAssert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be displayed.");
        textBlock.clickReadMoreButton();
        softAssert.assertTrue(textBlock.checkExpanded(initialCount), "Text should expand after clicking 'Трохи ще'.");
        softAssert.assertTrue(textBlock.isReadLessButtonDisplayed(), "'Дещо менше' button should be visible after clicking 'Трохи ще'.");

        //step : click on button 'Дещо менше'
        textBlock.clickReadLessButton();
        softAssert.assertTrue(textBlock.checkCollapsed(initialCount), "Text should collapse to initial state after clicking 'Дещо менше'.");
        softAssert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be visible after clicking 'Дещо менше'.");
        softAssert.assertAll();
    }

    @Issue("81")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verify that video play/pause")
    public void testStreetCodeVideoClick(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);
        streetCodePage.scrollToElement(streetCodePage.getTextBlock().getVideoPlayer());
        StreetCodeTextBlockComponent textBlock = streetCodePage.getTextBlock();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(textBlock.isVideoVisible(), "Video should be visible.");


        softAssert.assertTrue(textBlock.isPlayButtonVisible(), "Play button should be visible.");
        textBlock.clickPlayButton();
        softAssert.assertTrue(textBlock.isPauseButtonVisible(), "Video is not playing.");
        textBlock.clickPauseButton();
        softAssert.assertFalse(textBlock.isPauseButtonVisible(), "Video is not pausing.");
        driver.switchTo().defaultContent();
        softAssert.assertAll();
    }

    @Issue("81")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class, priority = 1)
    @Description("Verify that all links in additional text are clickable")
    public void testStreetCodeClickLinksInAdditionalText(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);
        StreetCodeTextBlockComponent textBlock = streetCodePage.getTextBlock();
        List<String> links = textBlock.getLinksInNewsContent();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(textBlock.isAdditionalTextDisplayed(), "Additional text should be display");
        softAssert.assertTrue(!links.isEmpty(), "No links found in the text block!");
        for (String link : links) {
            driver.get(link);
            String currentUrl = driver.getCurrentUrl();

            softAssert.assertTrue(currentUrl.equals(link),
                    "Redirection failed or URL mismatch for link: " + link);

        }
        softAssert.assertAll();
    }
}

