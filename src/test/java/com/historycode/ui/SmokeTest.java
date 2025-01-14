package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.StreetCodeTextBlockComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Optional;

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
    @Description("Verification of the teaser text Length")
    public void testTeaserTextLength() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(1);

        String teaserText = streetCodePage.getMainCard().getDescription();

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

    @Issue("81")
    @Test(priority = 1)
    @Description("[Text and Video] Verify that all buttons are clickable")
    public void testStreetCodeClick() {
        HomePage homePage = new HomePage(driver);
        StreetCodePage streetCodePage = homePage.clickPersonCardCarouselItem(0);

        //step 1 : click on button 'Трохи ще'
        Optional<StreetCodeTextBlockComponent> optionalStreetCodeTextBlock = streetCodePage.getTextBlock();
        if (optionalStreetCodeTextBlock.isPresent()) {
            StreetCodeTextBlockComponent streetCodeTextBlockComponent = optionalStreetCodeTextBlock.get();
            Assert.assertTrue(streetCodeTextBlockComponent.isReadMoreButtonDisplayed(), "'Трохи ще' button should be displayed.");

            streetCodePage.scrollToElement(streetCodeTextBlockComponent.getReadMoreButton());
            streetCodeTextBlockComponent.clickReadMoreButton();

            streetCodePage.waitUntilElementVisible(streetCodeTextBlockComponent.getMainTextContent());
            Assert.assertTrue(streetCodeTextBlockComponent.getMainTextContent().isDisplayed(), "All available text should be visible after clicking 'Трохи ще'.");

            streetCodePage.waitUntilElementVisible(streetCodeTextBlockComponent.getReadLessButton());
            Assert.assertTrue(streetCodeTextBlockComponent.isReadLessButtonDisplayed(), "'Дещо менше' button should be visible after clicking 'Трохи ще'.");
        } else {
            Assert.fail("StreetCodeTextBlockComponents is not present on the page.");
        }
    }
}
