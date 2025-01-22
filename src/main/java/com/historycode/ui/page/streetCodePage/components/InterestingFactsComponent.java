package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.InterestingFactsCarousel;
import com.historycode.ui.page.streetCodePage.modals.InterestingFactsModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InterestingFactsComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='wow-facts']//h1[@class='blockHeadingText']")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='interestingFactsContainer ']")
    private WebElement carouselRoot;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    protected WebElement leftArrow;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    protected WebElement rightArrow;

    @Getter
    private final InterestingFactsModal interestingFactsModal;
    @Getter
    private final InterestingFactsCarousel carousel;

    public InterestingFactsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new InterestingFactsCarousel(driver, carouselRoot);
        this.interestingFactsModal = new InterestingFactsModal(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    public List<InterestingFactsCardComponent> getFactCards() {
        return carousel.getCards();
    }

    public void clickNextSlide() {
        rightArrow.click();
    }

    public void clickPreviousSlide() {
        leftArrow.click();
    }

    public void clickFactCard(int index) {
        if (index >= 0 && index < getFactCards().size()) {
            getFactCards().get(index).clickCard();
        }
    }

    public void goToSlide(int index) {
        carousel.goToSlide(index);
    }

    public int getCurrentSlideIndex() {
        return carousel.getCurrentSlideIndex();
    }

    public int getTotalSlides() {
        return carousel.getTotalSlides();
    }
}
