package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SmokeTest extends BaseTestRunner {

    @Issue("91")
    @Test
    public void testOpenPage(){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().scrollToEndOfPage();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]")).isDisplayed());
    }

    @Epic("StreetCode page")
    @Test(description = "Verification of Teaser Text Length", priority = 1)
    public void testTeaserTextLength() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(1);

        List<String> paragraphs = streetCodePage.getMainCard().getDescriptions();

        int paragraphCount = paragraphs.size();

        if (paragraphCount == 1) {
            Assert.assertTrue(paragraphs.getFirst().length() <= 520, "Description is too long for one paragraph.");
        } else if (paragraphCount == 2) {
            Assert.assertTrue((paragraphs.get(0).length() + paragraphs.get(1).length()) <= 455, "Description is too long for two paragraphs.");
        } else {
            throw new AssertionError("Text contains more than two paragraphs.");
        }

        Assert.assertFalse(streetCodePage.getMainCard().isTeaserTextOverflowing());
    }
}