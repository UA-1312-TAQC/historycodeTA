package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;
import java.util.function.Function;

public abstract class CarouselComponent<T extends BaseComponent> extends BaseComponent {

    @FindBy(xpath = ".//button[contains(@class, 'slick-arrow') and contains(@class, 'slick-prev')]")
    protected WebElement leftArrow;

    @FindBy(xpath = ".//button[contains(@class, 'slick-arrow') and contains(@class, 'slick-next')]")
    protected WebElement rightArrow;
    @Getter
    @FindBy(xpath = ".//div[contains(@class,'slick-slide') and contains(@class,'slick-active')]")
    public WebElement slickActive;

    public CarouselComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public void clickLeftArrow() {
        waitUntilElementVisible(leftArrow);
        leftArrow.click();
    }


    public void clickRightArrow() {
        waitUntilElementVisible(rightArrow);
        rightArrow.click();
    }

    public void safeClickRightArrow() {
        String currentId = getActiveSlideId();
        clickRightArrow();
        wait.until(d -> !getActiveSlideId().equals(currentId));
    }

    public void swipe(int xOffset) {
        try {
            int startX = slickActive.getLocation().getX() + (slickActive.getSize().getWidth() / 2);
            int startY = slickActive.getLocation().getY() + (slickActive.getSize().getHeight() / 2);

            String currentActiveIndex = getActiveSlideId();

            new Actions(driver)
                    .moveToElement(slickActive, 0, 0)
                    .moveByOffset(startX - slickActive.getLocation().getX(), startY - slickActive.getLocation().getY())
                    .clickAndHold()
                    .moveByOffset(xOffset, 0)
                    .release()
                    .perform();

            wait.until(d -> !getActiveSlideId().equals(currentActiveIndex));

        } catch (Exception e) {
            System.err.println("Error during swipe: " + e.getMessage());
        }
    }


    public void moveToSlide(int targetIndex) {
        int maxAttempts = 20;
        int attempts = 0;

        String currentIndexStr = getActiveSlideId();
        int currentIndex = Integer.parseInt(currentIndexStr);

        while (currentIndex != targetIndex && attempts < maxAttempts) {
            clickRightArrow();

            currentIndexStr = getActiveSlideId();
            currentIndex = Integer.parseInt(currentIndexStr);
            attempts++;
        }

        if (currentIndex != targetIndex) {
            throw new IllegalStateException("Failed to reach slide index " + targetIndex
                    + " after " + attempts + " attempts. Current index: " + currentIndex);
        }
    }

    public void moveToSlideUsingSwipe(int targetIndex) {
        int maxAttempts = 20;
        int attempts = 0;

        String currentIndexStr = getActiveSlideId();
        int currentIndex = Integer.parseInt(currentIndexStr);

        while (currentIndex != targetIndex && attempts < maxAttempts) {
            int xOffset = (currentIndex < targetIndex) ? -200 : 0; // Left swipe
            swipe(xOffset);

            currentIndexStr = getActiveSlideId();
            currentIndex = Integer.parseInt(currentIndexStr);
            attempts++;
        }

        if (currentIndex != targetIndex) {
            throw new IllegalStateException("Failed to reach slide index " + targetIndex
                    + " after " + attempts + " attempts. Current index: " + currentIndex);
        }
    }

    public String getActiveSlideId() {
        return getSlickActive().getAttribute("data-index");
    }

    public T scrollToCardElement(Function<T, Boolean> condition) {
        int totalSlides = getCarouselItems().size();
        for (int i = 0; i < totalSlides; i++) {
            T activeSlide = getActiveSlideComponent();
            if (condition.apply(activeSlide)) {
                return activeSlide;
            }
            safeClickRightArrow();
        }
        throw new IllegalStateException("No card matching the condition was found.");
    }

    public T scrollToCardElementUsingSwipe(Function<T, Boolean> condition) {
        int maxAttempts = 20;
        int attempts = 0;

        while (attempts < maxAttempts) {
            T activeSlide = getActiveSlideComponent();
            if (condition.apply(activeSlide)) {
                return activeSlide;
            }

            swipe(-200); // Left swipe
            attempts++;
        }

        throw new IllegalStateException("No card matching the condition was found after " + maxAttempts + " attempts.");
    }

    public abstract T getActiveSlideComponent();

    public abstract List<T> getCarouselItems();
}
