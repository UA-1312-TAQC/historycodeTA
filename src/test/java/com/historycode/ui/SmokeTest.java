package com.historycode.ui;


import com.historycode.ui.elements.BreadcrumbsElement;
import com.historycode.ui.page.HistoryCodePage.CatalogComponent;
import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class SmokeTest extends BaseTestRunner {

    @Issue("91")
    @Test
    public void testOpenPage(){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().scrollToEndOfPage();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]")).isDisplayed());
    }

    @SneakyThrows
    @Issue("78")
    @Test
    public void testOpenPreviousPage(){
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(1);
        BreadcrumbsElement breadcrumbs = streetCodePage.getBreadcrumbs();
        StreetCodesPage streetCodesPage = breadcrumbs.clickCatalog();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/catalog"), "Navigation to catalog failed");
    }

    @Issue("73")
    @Test(priority = 1)
    @Description("Verification of the teaser text Length")
    public void testTeaserTextLength() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(1);

        String teaserText = streetCodePage.getMainCard().getTeaserText();

        //TODO: criteria paragraphs
        String[] paragraphs = teaserText.split("\n");
        int paragraphCount = paragraphs.length;
        int characterCount = teaserText.replace("\n", "").length();

        Assert.assertTrue(paragraphCount <= 2, "Text contains more than 2 paragraphs.");

        if (paragraphCount == 1) {
            Assert.assertTrue(characterCount <= 520, "Description is too long for one paragraph.");
        } else if (paragraphCount == 2) {
            Assert.assertTrue(characterCount <= 455, "Description is too long for two paragraphs.");
        }

        //TODO: criteria overflow/truncated
        Assert.assertFalse(streetCodePage.getMainCard().isTeaserTextOverflowing());
    }
}
