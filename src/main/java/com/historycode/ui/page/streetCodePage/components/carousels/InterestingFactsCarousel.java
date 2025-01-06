package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import com.historycode.ui.page.streetCodePage.components.PaginationComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//button[contains(@class, 'slick-arrow slick-prev')]")
    private WebElement leftArrow;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//button[contains(@class, 'slick-arrow slick-next')]")
    private WebElement rightArrow;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//ul[@class='slick-dots']")
    private WebElement paginationNode;

    private List<InterestingFactsCardComponent> factCards;
    @Getter
    private PaginationComponent pagination;

    public InterestingFactsCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCards = factCardNodes.stream()
                .map(node -> new InterestingFactsCardComponent(driver, node))
                .collect(Collectors.toList());
        this.pagination = new PaginationComponent(driver, paginationNode);
    }

    public void clickNext() {
        if (hasArrows()) {
            rightArrow.click();
        }
    }

    public void clickPrevious() {
        if (hasArrows()) {
            leftArrow.click();
        }
    }

    public void goToSlide(int index) {
        pagination.selectDot(index);
    }

    public int getCurrentSlideIndex() {
        return pagination.getActiveIndex();
    }

    public int getTotalSlides() {
        return pagination.getTotalDots();
    }

    public List<InterestingFactsCardComponent> getCards() {
        return factCards;
    }
}
