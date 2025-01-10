package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTestRunner {

    @Issue("91")
    @Test
    public void testOpenPage() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().scrollToEndOfPage();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]")).isDisplayed());
    }

    @Issue("73")
    @Test(priority = 1)
    @Description("Verification of the teaser text Length for one paragraph")
    public void testTeaserTextLengthForOneParagraph() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(1);

        String paragraph = streetCodePage.getMainCard().getDescription();

        Assert.assertTrue(paragraph.length() <= 520, "Description is too long for one paragraph.");

        Assert.assertFalse(streetCodePage.getMainCard().isTeaserTextOverflowing());
    }
}