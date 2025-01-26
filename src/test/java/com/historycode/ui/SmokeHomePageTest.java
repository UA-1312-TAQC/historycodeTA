package com.historycode.ui;

import com.historycode.ui.page.HistoryCodePage.HistoryCodePage;
import com.historycode.ui.page.homePage.*;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Slf4j
@Epic("Home Page")
@Story("Smoke Test")
@Issue("517")
public class SmokeHomePageTest extends BaseTestRunner {

    private HomePage homePage;

    @BeforeMethod
    public void setupHomePage() {
        homePage = new HomePage(driver);
        log.info("HomePage initialized.");
        log.debug("Driver URL: {}", driver.getCurrentUrl());
    }

    @Test
    @Description("Verify news carousel length, navigate each slide, collect and validate data.")
    public void verifyNewsCarouselSlides() {
        log.info("Starting test: Verify news carousel slides.");
        homePage.scrollUntilElementIsVisible(homePage.getNewsCarouselElement());
        NewsCardCarousel newsCarousel = homePage.getNewsCarousel();

        List<NewsCardComponent> slides = newsCarousel.getCarouselItems();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(slides.isEmpty(), "No slides found in the news carousel.");

        int totalSlides = slides.size();
        log.info("Total slides in the news carousel: {}", totalSlides);

        String previousIndex = newsCarousel.getActiveSlideId();

        for (int i = 0; i < totalSlides; i++) {
            log.debug("Navigating to slide index: {}", i);
            newsCarousel.moveToSlide(i);

            String currentIndex = newsCarousel.getActiveSlideId();
            if (i > 0) {
                softAssert.assertNotEquals(
                        currentIndex,
                        previousIndex,
                        "Active slide index did not change after navigating. Previous: " + previousIndex + ", Current: " + currentIndex
                );
            }

            NewsCardComponent activeSlide = new NewsCardComponent(driver, newsCarousel.getSlickActive());
            try {
                String imageUrl = activeSlide.getNewsImageUrl();
                String title = activeSlide.getTitle();
                String publishDate = activeSlide.getPublishDate();
                String summary = activeSlide.getSummary();

                log.info("Slide #{} -> Index: {}, Title: {}, PublishDate: {}, Summary: {}",
                        i, currentIndex, title, publishDate, summary);

                softAssert.assertTrue(imageUrl != null && !imageUrl.trim().isEmpty(), "Image URL must not be blank.");
                softAssert.assertFalse(title.trim().isEmpty(), "Title must not be blank.");
                softAssert.assertFalse(publishDate.trim().isEmpty(), "Publish date must not be blank.");
                softAssert.assertFalse(summary.trim().isEmpty(), "Summary must not be blank.");

            } catch (NoSuchElementException e) {
                log.warn("Optional element is missing on slide index: {}", i, e);
            } catch (Exception e) {
                log.error("Failed to retrieve or validate data for slide index: {}", i, e);
                softAssert.fail("An error occurred while processing slide data: " + e.getMessage());
            }

            previousIndex = currentIndex;
        }

        softAssert.assertAll();
        log.info("Finished test: Verify news carousel slides.");
    }

    @Test
    @Description("Verify team carousel length, navigate each slide, collect and validate data.")
    public void verifyTeamCarouselSlides() {
        log.info("Starting test: Verify team carousel slides.");
        homePage.scrollUntilElementIsVisible(homePage.getTeamCarouselElement());
        TeamCardCarousel teamCarousel = homePage.getTeamCarousel();

        List<TeamCardComponent> slides = teamCarousel.getCarouselItems();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(slides.isEmpty(), "No slides found in the team carousel.");

        int totalSlides = slides.size();
        log.info("Total slides in the team carousel: {}", totalSlides);

        String previousIndex = teamCarousel.getActiveSlideId();

        for (int i = 0; i < totalSlides; i++) {
            log.debug("Navigating to slide index: {}", i);
            teamCarousel.moveToSlide(i);

            String currentIndex = teamCarousel.getActiveSlideId();
            if (i > 0) {
                softAssert.assertNotEquals(
                        currentIndex,
                        previousIndex,
                        "Active slide index did not change after navigating. Previous: " + previousIndex + ", Current: " + currentIndex
                );
            }

            TeamCardComponent activeSlide = new TeamCardComponent(driver, teamCarousel.getSlickActive());
            try {
                String photoUrl = activeSlide.getPhotoUrl();
                String name = activeSlide.getMemberName();
                String position = activeSlide.getMemberPosition();
                List<String> socialLinks = activeSlide.getSocialLinks();

                log.info("Slide #{} -> Index: {}, Name: {}, Position: {}, Social Links: {}",
                        i, currentIndex, name, position, socialLinks);

                softAssert.assertTrue(photoUrl != null && !photoUrl.trim().isEmpty(), "Photo URL must not be blank.");
                softAssert.assertFalse(name.trim().isEmpty(), "Team member name must not be blank.");
                softAssert.assertFalse(position.trim().isEmpty(), "Team member position must not be blank.");
                softAssert.assertTrue(socialLinks != null && !socialLinks.isEmpty(), "Team member must have at least one social link.");

            } catch (NoSuchElementException e) {
                log.warn("Optional element is missing on slide index: {}", i, e);
            } catch (Exception e) {
                log.error("Failed to retrieve or validate data for slide index: {}", i, e);
                softAssert.fail("An error occurred while processing slide data: " + e.getMessage());
            }

            previousIndex = currentIndex;
        }

        softAssert.assertAll();
        log.info("Finished test: Verify team carousel slides.");
    }

    @Test
    @Step("Verify that the news carousel navigates correctly and loops from first to last slide.")
    @Description("Verify the navigation functionality of the news carousel, including looping from first to last slide.")
    public void verifyNavigation() {
        log.info("Starting test: Verify navigation in news carousel.");

        homePage.scrollUntilElementIsVisible(homePage.getNewsCarouselElement());
        NewsCardCarousel newsCarousel = homePage.getNewsCarousel();

        SoftAssert softAssert = new SoftAssert();

        String firstSlideId = newsCarousel.getActiveSlideId();
        log.debug("Initial slide ID: {}", firstSlideId);

        newsCarousel.clickRightArrow();
        String secondSlideId = newsCarousel.getActiveSlideId();
        log.debug("After clicking right arrow, active slide ID: {}", secondSlideId);
        softAssert.assertNotEquals(firstSlideId, secondSlideId, "Slide ID did not change after clicking right arrow.");

        newsCarousel.clickLeftArrow();
        String thirdSlideId = newsCarousel.getActiveSlideId();
        log.debug("After clicking left arrow, active slide ID: {}", thirdSlideId);
        softAssert.assertNotEquals(secondSlideId, thirdSlideId, "Slide ID did not change after clicking left arrow.");

        newsCarousel.moveToSlide(0);
        newsCarousel.clickLeftArrow();
        String loopedSlideId = newsCarousel.getActiveSlideId();
        log.debug("After looping back, active slide ID: {}", loopedSlideId);

        int lastSlideIndex = newsCarousel.getCarouselItems().size() - 1;
        newsCarousel.moveToSlide(lastSlideIndex);
        String lastSlideId = newsCarousel.getActiveSlideId();
        log.debug("Last slide ID: {}", lastSlideId);

        softAssert.assertEquals(loopedSlideId, lastSlideId, "Slide ID after looping does not match the last slide.");

        softAssert.assertAll();

        log.info("Finished test: Verify navigation in news carousel.");
    }


    @Test
    @Step("Scroll through the news carousel to find a card with a specific title.")
    @Description("Scroll through the news carousel and validate the card with the expected title.")
    public void testScrollToCardByTitle() {
        log.info("Starting test: Scroll to card by title.");

        homePage.scrollUntilElementIsVisible(homePage.getNewsCarouselElement());
        NewsCardCarousel newsCarousel = homePage.getNewsCarousel();

        String expectedTitle = "Уроки Незламності — старт!";
        log.debug("Expected title: {}", expectedTitle);

        NewsCardComponent foundCard = newsCarousel.scrollToCardElement(card -> {
            String title = card.getTitle();
            log.debug("Checking card with title: {}", title);
            return title.equals(expectedTitle);
        });

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(foundCard, "No card with the expected title was found.");

        String actualTitle = foundCard.getTitle();
        log.debug("Found card title: {}", actualTitle);
        softAssert.assertEquals(actualTitle, expectedTitle, "Card title does not match the expected value.");

        softAssert.assertAll();
        log.info("Finished test: Scroll to card by title.");
    }

    @Test
    @Step("Scroll through the news carousel to find a card with a specific summary and validate its title.")
    @Description("Scroll through the news carousel, find a card with the expected summary, and verify its title matches the expected value.")
    public void testScrollToCardBySummaryAndCheckTitle() {
        log.info("Starting test: Scroll to card by summary and validate title.");
        homePage.scrollUntilElementIsVisible(homePage.getNewsCarouselElement());
        NewsCardCarousel newsCarousel = homePage.getNewsCarousel();

        String expectedSummary = "news";
        String expectedTitle = "news11я";
        log.debug("Expected summary: {}, Expected title: {}", expectedSummary, expectedTitle);

        NewsCardComponent foundCard = newsCarousel.scrollToCardElement(card -> {
            String summary = card.getSummary();
            log.debug("Checking card with summary: {}", summary);
            return summary.equals(expectedSummary);
        });

        Assert.assertNotNull(foundCard, "No card with the expected summary was found.");

        String actualTitle = foundCard.getTitle();
        log.debug("Found card title: {}", actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match the expected value.");

        log.info("Test completed successfully: Card with the expected summary and title found.");
    }

    @Test
    @Description("Verify person carousel length, swipe through each slide, collect and validate data.")
    public void verifyPersonCarouselSlides() {
        log.info("Starting test: Verify person carousel slides.");

        homePage.scrollUntilElementIsVisible(homePage.getPersonCarouselElement());
        PersonCardCarousel personCarousel = homePage.getPersonsCarousel();

        List<PersonCardComponent> slides = personCarousel.getCarouselItems();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(slides.isEmpty(), "No slides found in the person carousel.");
        int totalSlides = slides.size();
        log.info("Total slides in the person carousel: {}", totalSlides);

        String previousSlideId = null;

        for (int i = 0; i < totalSlides; i++) {
            log.debug("Processing slide index: {}", i);

            // Swipe to the next slide if not the first one
            if (i > 0) {
                personCarousel.swipe(-200);
            }

            PersonCardComponent activeSlide = personCarousel.getActiveSlideComponent();
            softAssert.assertNotNull(activeSlide, "Active slide is null after swipe.");

            if (activeSlide != null) {
                try {
                    String personName = activeSlide.getPersonName();
                    String category = activeSlide.getCategory();
                    String description = activeSlide.getDescription();
                    String imageSrc = activeSlide.getImageSrc();
                    WebElement linkToStreetCodePage = activeSlide.getToHistoryCodePage();

                    log.info("Slide #{} -> Name: {}, Category: {}, Description: {}, ImageSrc: {}",
                            i, personName, category, description, imageSrc);

                    // Validating slide data
                    softAssert.assertTrue(personName != null && !personName.trim().isEmpty(),
                            "Person name must not be blank.");
                    softAssert.assertTrue(category == null || !category.trim().isEmpty(),
                            "Category must either be null or not blank.");
                    softAssert.assertTrue(description != null && !description.trim().isEmpty(),
                            "Description must not be blank.");
                    softAssert.assertTrue(imageSrc != null && !imageSrc.trim().isEmpty(),
                            "Image source must not be blank.");
                    softAssert.assertTrue(linkToStreetCodePage != null,
                            "Image source must not be blank.");
                } catch (NoSuchElementException e) {
                    log.warn("Optional element is missing on slide index: {}", i, e);
                } catch (Exception e) {
                    log.error("Failed to retrieve or validate data for slide index: {}", i, e);
                    softAssert.fail("An error occurred while processing slide data: " + e.getMessage());
                }
            } else {
                log.warn("Active slide is null at index: {}", i);
            }
        }

        softAssert.assertAll();
        log.info("Finished test: Verify person carousel slides.");
    }

    @Test
    @Step("Scroll through the person carousel to find a card with a specific name and validate its description.")
    @Description("Scroll through the person carousel, find a card with the expected name, and verify its description matches the expected value.")
    public void testScrollPersonalCarouselAndCheckTitleAndDescription() {
        log.info("Starting test: Scroll to card by name and validate description.");
        homePage.scrollUntilElementIsVisible(homePage.getPersonCarouselElement());
        PersonCardCarousel personCarousel = homePage.getPersonsCarousel();

        String expectedName = "Христина Скачківська-Сушко";
        String expectedDescription = "«Одна з кращих дочок» держави, за Симоном Петлюрою. Смілива військова медикиня в лавах Січових Стрільців. Єдина жінка-старшина вже в Армії УНР, яка виборола в боях і заслужила турботою про здоров’я побратимів свій офіцерський ранг. Громадська діячка, опікунка, захисниця інтересів українців у таборах як для інтернованих вояків, так і для переміщених осіб пізніше. Аристократка, пов’язана з російським дворянством, що стала борчинею за українську незалежність, дух якої не зламали ані більшовицький полон, ані поранення.";
        log.debug("Expected Name: {}, Expected Description: {}", expectedName, expectedDescription);

        PersonCardComponent foundCard = personCarousel.scrollToCardElementUsingSwipe(card -> {
            String name = card.getPersonName();
            log.debug("Checking card with name: {}", name);
            return name.equals(expectedName);
        });

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(foundCard, "No card with the expected name was found.");
        String actualDescription = foundCard.getDescription();
        log.debug("Found card description: {}", actualDescription);
        softAssert.assertEquals(actualDescription, expectedDescription, "Description does not match the expected value.");

        softAssert.assertAll();
        log.info("Test completed successfully: Card with the expected name and description found.");
    }

    @Test
    @Description("Verify clicking the 'toHistoryCode' button navigates to the correct page.")
    @Step("Click on 'toHistoryCode' button and verify navigation to the new page.")
    public void testClickToHistoryCodeButton() {
        log.info("Starting test: Click on 'toHistoryCode' button and verify navigation.");
        homePage.scrollUntilElementIsVisible(homePage.getPersonCarouselElement());
        homePage.getPersonsCarousel()
                .getActiveSlideComponent()
                .clickToHistoryCode();

        HistoryCodePage historyCodePage = new HistoryCodePage(driver);
        SoftAssert softAssert = new SoftAssert();

        WebElement historyCodeLink = historyCodePage.getStreetsCodesLink();
        log.info("Validating the header on the new page: '{}'.", historyCodeLink);
        softAssert.assertNotNull(historyCodeLink, "The page title is null.");
        softAssert.assertFalse(!historyCodeLink.isDisplayed(), "The page title is blank.");

        softAssert.assertAll();

        log.info("Test completed successfully: Verified navigation to the new page after clicking the button.");
    }
}
