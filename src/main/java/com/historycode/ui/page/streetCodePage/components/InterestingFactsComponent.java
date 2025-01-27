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
    @Getter
    @FindBy(xpath = ".//h1[@class='blockHeadingText']")
    private WebElement title;

    @Getter
    @FindBy(xpath = ".//div[@class='interestingFactsContainer ']")
    private WebElement carouselRoot;

    @Getter
    private final InterestingFactsModal interestingFactsModal;
    @Getter
    private final InterestingFactsCarousel carousel;

    public InterestingFactsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new InterestingFactsCarousel(driver, carouselRoot);
        this.interestingFactsModal = new InterestingFactsModal(driver, rootElement);
    }

    public String getTitleName() {
        return title.getText();
    }

    public List<InterestingFactsCardComponent> getFactCards() {
        return carousel.getCards();
    }

    public void clickNextSlide() {
        carousel.clickNextArrow();
    }

    public void clickPreviousSlide() {
        carousel.clickPreviousArrow();
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
    public int getCurrentCardIndex() {
        return carousel.getCurrentCardIndex();
    }

    public int getTotalSlides() {
        return carousel.getTotalSlides();
    }

    public int getActiveWowFactsSlickDotIndex() {
        return carousel.getActiveWowFactsSlickDotIndex();
    }

    public int getLastWowFactsSlickDotIndex() {
        return carousel.getLastWowFactsSlickDotIndex();
    }

    public void clickWowFactsSlickDotByIndex(int index) {
        carousel.clickWowFactsSlickDotByIndex(index);
    }



}
