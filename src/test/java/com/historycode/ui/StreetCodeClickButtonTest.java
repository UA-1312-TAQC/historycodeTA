package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.StreetCodeTextBlockComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class StreetCodeClickButtonTest extends BaseTestRunner {

    @Issue("81")
    @Test(priority = 1)
    @Description("[Text and Video] Verify that all buttons are clickable")
    public void testStreetCodeClick() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(0);
        StreetCodeTextBlockComponent textBlock = streetCodePage.getTextBlock();
        int initialCount = textBlock.getParagraphCount();

        //step 2 : click on button 'Трохи ще'
        Assert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be displayed.");
        Assert.assertTrue(textBlock.checkExpanded(), "Text should expand after clicking 'Трохи ще'.");
        Assert.assertTrue(textBlock.isReadLessButtonDisplayed(), "'Дещо менше' button should be visible after clicking 'Трохи ще'.");

        //step 4 : click on button 'Дещо менше'
        Assert.assertTrue(textBlock.checkCollapsed(initialCount), "Text should collapse to initial state after clicking 'Дещо менше'.");
        Assert.assertTrue(textBlock.isReadMoreButtonDisplayed(), "'Трохи ще' button should be visible after clicking 'Дещо менше'.");
    }
}

