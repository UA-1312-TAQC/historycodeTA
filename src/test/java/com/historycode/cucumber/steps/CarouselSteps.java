package com.historycode.cucumber.steps;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.homePage.*;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.SoftAssert;
import java.util.List;

import static org.testng.Assert.assertNotNull;

@Slf4j
public class CarouselSteps extends BaseTestRunner {

    private final HomePage homePage;
    private final SoftAssert softAssert = new SoftAssert();
    private CarouselComponent<?> carousel;

    public CarouselSteps() {
        homePage = new HomePage(driver);
    }

    @Given("I open the Home Page")
    public void openHomePage() {
        driver.get(testValueProvider.getBaseUIUrl());
        log.info("Opened Home Page.");
    }

    private CarouselComponent<?> getCarousel(String carouselType) {
        return switch (carouselType) {
            case "news" -> homePage.getNewsCarousel();
            case "team" -> homePage.getTeamCarousel();
            case "person" -> homePage.getPersonsCarousel();
            default -> throw new IllegalArgumentException("Unknown carousel type: " + carouselType);
        };
    }

    @When("I scroll to the {string} carousel")
    public void scrollToCarousel(String carouselType) {
        this.carousel = getCarousel(carouselType);
        homePage.scrollUntilElementIsVisible(carousel.getSlickActive());
        log.info("Scrolled to {} carousel", carouselType);
    }

    @Then("I should see at least one slide in the {string} carousel")
    public void verifyCarouselSlides(String carouselType) {
        this.carousel = getCarousel(carouselType);
        List<?> slides = carousel.getCarouselItems();
        softAssert.assertFalse(slides.isEmpty(), "No slides found in the " + carouselType + " carousel.");
        log.info("Verified that {} carousel contains slides", carouselType);
        softAssert.assertAll();
    }

    @Then("I should be able to navigate through each slide in the {string} carousel")
    public void navigateAndValidateCarousel(String carouselType) {
        this.carousel = getCarousel(carouselType);
        List<?> slides = carousel.getCarouselItems();
        String previousIndex = carousel.getActiveSlideId();

        for (int i = 0; i < slides.size(); i++) {
            carousel.moveToSlide(i);
            String currentIndex = carousel.getActiveSlideId();
            if (i > 0) {
                softAssert.assertNotEquals(currentIndex, previousIndex, "Slide did not change in " + carouselType + " carousel.");
            }
            previousIndex = currentIndex;
        }
        log.info("Navigated through all slides in {} carousel", carouselType);
        softAssert.assertAll();
    }

    @Then("I should be able to navigate right and left in the {string} carousel")
    public void verifyCarouselNavigation(String carouselType) {
        this.carousel = getCarousel(carouselType);
        String firstSlideId = carousel.getActiveSlideId();

        carousel.safeClickRightArrow();
        String secondSlideId = carousel.getActiveSlideId();
        softAssert.assertNotEquals(firstSlideId, secondSlideId, "Slide did not change after right arrow click in " + carouselType + " carousel.");

        carousel.safeClickLeftArrow();
        String thirdSlideId = carousel.getActiveSlideId();
        softAssert.assertNotEquals(secondSlideId, thirdSlideId, "Slide did not change after left arrow click in " + carouselType + " carousel.");

        log.info("Navigation via left and right arrows verified in {} carousel", carouselType);
        softAssert.assertAll();
    }

    @Then("the {string} carousel should loop correctly from first to last slide")
    public void verifyCarouselLooping(String carouselType) {
        this.carousel = getCarousel(carouselType);

        carousel.moveToSlide(0);
        carousel.safeClickLeftArrow();
        String loopedSlideId = carousel.getActiveSlideId();

        int lastSlideIndex = carousel.getCarouselItems().size() - 1;
        carousel.moveToSlide(lastSlideIndex);
        String lastSlideId = carousel.getActiveSlideId();

        softAssert.assertEquals(loopedSlideId, lastSlideId, carouselType + " carousel did not loop correctly.");
        log.info("Looping verified in {} carousel", carouselType);
        softAssert.assertAll();
    }

    @Then("I should find a card with title {string} in the {string} carousel")
    public void testScrollToCard(String expectedTitle, String carouselType) {
        this.carousel = getCarousel(carouselType);
        BaseComponent foundCard = carousel.scrollToCardElement(card -> {
            if (card instanceof NewsCardComponent) {
                return ((NewsCardComponent) card).getTitle().equals(expectedTitle);
            } else if (card instanceof PersonCardComponent) {
                return ((PersonCardComponent) card).getPersonName().equals(expectedTitle);
            } else if (card instanceof TeamCardComponent) {
                return ((TeamCardComponent) card).getMemberName().equals(expectedTitle);
            }
            return false;
        });

        assertNotNull(foundCard, "No card with the expected title was found in " + carouselType + " carousel.");
        log.info("Found card with title '{}' in {} carousel", expectedTitle, carouselType);
        softAssert.assertAll();
    }

    @Then("I should find a card with title {string} in the {string} carousel and validate its description")
    public void testScrollToCardWithDescription(String expectedTitle, String carouselType) {
        this.carousel = getCarousel(carouselType);
        BaseComponent foundCard = carousel.scrollToCardElement(card -> {
            if (card instanceof NewsCardComponent) {
                return ((NewsCardComponent) card).getTitle().equals(expectedTitle);
            } else if (card instanceof PersonCardComponent) {
                return ((PersonCardComponent) card).getPersonName().equals(expectedTitle);
            } else if (card instanceof TeamCardComponent) {
                return ((TeamCardComponent) card).getMemberName().equals(expectedTitle);
            }
            return false;
        });

        assertNotNull(foundCard, "No card with the expected title was found in " + carouselType + " carousel.");

        String description = null;
        if (foundCard instanceof NewsCardComponent) {
            description = ((NewsCardComponent) foundCard).getSummary();
        } else if (foundCard instanceof PersonCardComponent) {
            description = ((PersonCardComponent) foundCard).getDescription();
        } else if (foundCard instanceof TeamCardComponent) {
            description = ((TeamCardComponent) foundCard).getMemberPosition();
        }

        assertNotNull(description, "Description should not be null for card with title '" + expectedTitle + "' in " + carouselType + " carousel.");
        log.info("Found card with title '{}' and validated description in {} carousel", expectedTitle, carouselType);
        softAssert.assertAll();
    }

    @Then("I should click the 'toHistoryCode' button in the {string} carousel and verify the page changes")
    public void testClickToHistoryCodeButton(String carouselType) {
        this.carousel = getCarousel(carouselType);
        BaseComponent activeSlide = carousel.getActiveSlideComponent();

        String initialUrl = driver.getCurrentUrl();

        if (activeSlide instanceof PersonCardComponent) {
            ((PersonCardComponent) activeSlide).clickToHistoryCode();
        } else {
            throw new IllegalStateException("The 'toHistoryCode' button is only available in the 'person' carousel.");
        }

        String newUrl = driver.getCurrentUrl();
        softAssert.assertNotEquals(newUrl, initialUrl, "Page URL did not change after clicking 'toHistoryCode' in " + carouselType + " carousel.");
        log.info("Navigation verified after clicking 'toHistoryCode' in {} carousel", carouselType);
        softAssert.assertAll();
    }
}

