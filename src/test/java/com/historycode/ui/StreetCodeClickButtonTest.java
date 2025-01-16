package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.StreetCodeTextBlockComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StreetCodeClickButtonTest extends BaseTestRunner {

    @Issue("81")
    @Test(priority = 1)
    @Description("[Text and Video] Verify that all buttons are clickable")
    public void testStreetCodeClick() {

        StreetCodeTextBlockComponent textBlock = new HomePage(driver)
                .clickPersonCardCarouselItem(0)
                .getTextBlock();
        int initialCount = textBlock.getParagraphCount();
        SoftAssert softAssert = new SoftAssert();
        //step 2 : click on button 'Трохи ще'
        softAssert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be displayed.");
        softAssert.assertTrue(textBlock.checkExpanded(), "Text should expand after clicking 'Трохи ще'.");
        softAssert.assertTrue(textBlock.isReadLessButtonDisplayed(), "'Дещо менше' button should be visible after clicking 'Трохи ще'.");

        //step 3 : is video playing/pause
        softAssert.assertTrue(textBlock.isVideoVisible(), "Video player should be visible.");
        textBlock.clickPlayButton();
        softAssert.assertTrue(textBlock.isVideoPlaying(), "Video is expected to be playing.");
        textBlock.clickPauseButton();
        softAssert.assertTrue(textBlock.isVideoPaused(), "Video is expected to be paused.");

        //step 4 : click on button 'Дещо менше'
        softAssert.assertTrue(textBlock.checkCollapsed(initialCount), "Text should collapse to initial state after clicking 'Дещо менше'.");
        softAssert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be visible after clicking 'Дещо менше'.");
        softAssert.assertAll();
    }
}

