package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

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

    public String getActiveSlideId() {
        return getSlickActive().getAttribute("data-index");
    }

    @Step("Move to slide with index = {targetIndex}")
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


    public abstract List<T> getCarouselItems();
}
