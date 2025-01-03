package com.historycode.ui.page.streetCodePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FactsCarousel extends BaseCarousel{
    @FindBy(xpath = ".//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//button[contains(@class, 'slick-arrow slick-prev')]")
    private WebElement leftArrow;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//button[contains(@class, 'slick-arrow slick-next')]")
    private WebElement rightArrow;

    @FindBy(xpath = ".//div[@class='interestingFactsSliderContainer']//ul[@class='slick-dots']")
    private WebElement pagination;

    private List<FactCardComponent> factCards;
    private PaginationComponent paginationComponent;

    public FactsCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCards = factCardNodes.stream()
                .map(node -> new FactCardComponent(driver, node))
                .collect(Collectors.toList());
        this.paginationComponent = new PaginationComponent(driver, pagination);
    }

    public void clickNext() {
        rightArrow.click();
    }

    public void clickPrevious() {
        leftArrow.click();
    }

    public List<FactCardComponent> getCards() {
        return factCards;
    }

    public PaginationComponent getPagination() {
        return paginationComponent;
    }
}
