package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;


public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[contains(@class, 'slick-slide slick-cloned')]//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//div[contains(@class, 'slick-current')]//div[@class='interestingFactSlide']")
    private WebElement factCardCurrentNode;

    @FindBy(xpath = ".//div[contains(@class, 'oneFactItem')]//div[@class='interestingFactSlide']")
    private WebElement oneCardNode;

    //TODO: remove this
    @FindBy(xpath = ".//button[@class='slick-arrow slick-next']")
    private WebElement nextButtonNode;

    @FindBy(xpath = ".//button[@class='slick-arrow slick-prev']")
    private WebElement prevButtonNode;

    @FindBy(xpath = ".//ul[@class='slick-dots']")
    private WebElement paginationNode;

    private final List<InterestingFactsCardComponent> factCards;

    public InterestingFactsCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCards = factCardNodes.stream()
                .map(node -> new InterestingFactsCardComponent(driver, node))
                .collect(Collectors.toList());
        initializePagination(driver);
    }

    public List<InterestingFactsCardComponent> getCards() {
        return factCards;
    }

    @Step("Get a card count.")
    public int getCardCount() {
        return factCards.size();
    }

    @Step("Check if the carousel is in the one card mode.")
    public boolean isOneCardPresent() {
        return (oneCardNode != null ) && (oneCardNode.isDisplayed());
    }

    @Step("Get a card title.")
    public String getCardTitle(int index) {
        return factCards.get(index).getTitle();
    }

    @Step("Click the 'Next' button.")
    public InterestingFactsCarousel clickNextButton() {
        clickDynamicElement(nextButtonNode);
        return this;
    }

    @Step("Get a current card title.")
    public String getCurrentNodeTitle(){
        waitUntilElementVisible(factCardCurrentNode);
        return factCardCurrentNode.findElement(By.xpath(".//p[@class = 'cardTextContainerTitle']")).getText();
    }

    @Step("Get a center of the element relative to the block.")
    public Point getElementCenterRelativeToBlock() {
        return getCenterRelativeToBlock(rootElement, oneCardNode);
    }

    //TODO: remove this
    @Override
    public boolean hasArrows() {
        try {
            return prevButtonNode.isDisplayed() && nextButtonNode.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean hasPagination() {
        try {
            return paginationNode.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
