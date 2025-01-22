package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.homePage.NewsCardCarousel;
import com.historycode.ui.page.homePage.NewsCardComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
@Epic("Home Page")
@Story("Smoke Test")
@Issue("517")
public class SmokeHomePageTest extends BaseTestRunner {

    private HomePage homePage;
    private NewsCardCarousel newsCarousel;

    @BeforeMethod
    public void setupCarousel() {
        homePage = new HomePage(driver);
        homePage.scrollUntilElementIsVisible(homePage.getNewsCarouselElement());
        newsCarousel = homePage.getNewsCarousel();
    }

    @Test
    @Description("Verify carousel length, navigate each slide, collect and validate data.")
    public void verifyNewsCarouselSlides() {
        List<NewsCardComponent> slides = newsCarousel.getCarouselItems();
        Assert.assertFalse(slides.isEmpty(), "No slides found in the news carousel.");
        int totalSlides = slides.size();
        log.info("Total slides in the news carousel: {}", totalSlides);

        String previousIndex = newsCarousel.getActiveSlideId();
        for (int i = 0; i < totalSlides; i++) {
            newsCarousel.moveToSlide(i);
            String currentIndex = newsCarousel.getActiveSlideId();
            if (i > 0) {
                Assert.assertNotEquals(
                        currentIndex,
                        previousIndex,
                        "Active slide index did not change after navigating."
                );
            }
            NewsCardComponent activeSlide = new NewsCardComponent(driver, newsCarousel.getSlickActive());

            String imageUrl = activeSlide.getNewsImageUrl();
            String title = activeSlide.getTitle();
            String publishDate = activeSlide.getPublishDate();
            String summary = activeSlide.getSummary();

            log.info("Slide #{} -> Index: {}, Title: {}, PublishDate: {}, Summary: {}",
                    i, currentIndex, title, publishDate, summary);

            Assert.assertTrue(imageUrl != null && !imageUrl.trim().isEmpty(), "Image URL must not be blank.");
            Assert.assertFalse(title.trim().isEmpty(), "Title must not be blank.");
            Assert.assertFalse(publishDate.trim().isEmpty(), "Publish date must not be blank.");
            Assert.assertFalse(summary.trim().isEmpty(), "Summary must not be blank.");

            previousIndex = currentIndex;
        }
    }

    @Test
    @Step("Verify that the carousel navigates correctly and loops from first to last slide.")
    public void verifyNavigation() {
        String firstSlideId = newsCarousel
                .getActiveSlideId();
        newsCarousel.clickRightArrow();
        String secondSlideId = newsCarousel
                .getActiveSlideId();
        Assert.assertNotEquals(firstSlideId, secondSlideId, "Slide ID did not change after clicking right arrow.");

        newsCarousel
                .clickLeftArrow();
        String thirdSlideId = newsCarousel
                .getActiveSlideId();
        Assert.assertNotEquals(secondSlideId, thirdSlideId, "Slide ID did not change after clicking left arrow.");

        newsCarousel
                .moveToSlide(0);
        newsCarousel
                .clickLeftArrow();
        String loopedSlideId = newsCarousel
                .getActiveSlideId();

        int lastSlideIndex = newsCarousel
                .getCarouselItems().size() - 1;
        newsCarousel
                .moveToSlide(lastSlideIndex);
        String lastSlideId = newsCarousel
                .getActiveSlideId();
        Assert.assertEquals(loopedSlideId, lastSlideId, "Slide ID after looping does not match the last slide.");
    }

    @Test
    @Description("Ensure that the 'Read More' link navigates away from the current page.")
    public void verifyReadMoreLink() {
        String currentUrl = driver.getCurrentUrl();
        newsCarousel
                .getCarouselItems()
                .getFirst()
                .clickReadMore();
        Assert.assertNotEquals(driver.getCurrentUrl(), currentUrl, "URL did not change after clicking 'Read More'.");
    }

    @Test
    @Description("Verify the carousel is visible and responsive on a smaller viewport.")
    public void verifyResponsiveness() {
        driver.manage().window().setSize(new Dimension(375, 812));
        Assert.assertTrue(homePage.getNewsCarouselElement().isDisplayed(), "Carousel is not displayed on a smaller viewport.");
        driver.manage().window().maximize();
    }

    @Test
    @Description("Verify the active slide ID changes when clicking the arrow.")
    public void verifyIdChangingAfterScroll() {
        String firstId = newsCarousel
                .getActiveSlideId();
        newsCarousel
                .clickRightArrow();
        String secondId = newsCarousel
                .getActiveSlideId();
        Assert.assertNotEquals(firstId, secondId, "Active slide ID did not change after clicking right arrow.");
    }
}
