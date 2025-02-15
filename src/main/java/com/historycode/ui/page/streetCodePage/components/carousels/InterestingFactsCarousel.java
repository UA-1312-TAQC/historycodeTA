package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[contains(@class, 'slick-slide slick-cloned')]//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = "//div[@id='wow-facts']//div[contains(@class, 'slick-slide')]")
    List<WebElement> allCads;

    @FindBy(xpath = ".//div[contains(@class, 'oneFactItem')]//div[@class='interestingFactSlide']")
    private WebElement oneCardNode;

    @FindBy(xpath = ".//div[@class ='slick-slide slick-active slick-center slick-current']")
    private WebElement activeCard;

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
        try {
            return oneCardNode.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Get a card title.")
    public String getCardTitle(int index) {
        return factCards.get(index).getTitle();
    }

    @Step("Get a current card title.")
    public String getCurrentNodeTitle() {
        waitUntilElementVisible(activeCard);
        return activeCard.getText();
    }

    @Override
    public BaseCarousel dynamicClickNextButton() {
        String currentTitle = getCurrentNodeTitle();
        BaseCarousel carusel = super.dynamicClickNextButton();
        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(activeCard, "textContent", currentTitle)
        ));
        return carusel;
    }

    @Step("Get a center of the element relative to the block.")
    public boolean isCardInCenterOfBlock() {
        return isElementInCenterOfBlock(rootElement, oneCardNode);
    }

    private boolean isElementInCenterOfBlock(WebElement block, WebElement element) {
        final double CENTER_ALIGNMENT_TOLERANCE = 10;
        double blockLeft, blockTop, blockWidth, blockHeight, elementLeft, elementTop, elementWidth, elementHeight;
        int scrollX, scrollY;

        try {
            blockLeft = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().left;", block))).doubleValue();
            blockTop = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().top;", block))).doubleValue();
            blockWidth = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().width;", block))).doubleValue();
            blockHeight = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().height;", block))).doubleValue();

            elementLeft = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().left;", element))).doubleValue();
            elementTop = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().top;", element))).doubleValue();
            elementWidth = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().width;", element))).doubleValue();
            elementHeight = ((Number) Objects.requireNonNull(threadJs
                    .executeScript("return arguments[0].getBoundingClientRect().height;", element))).doubleValue();

            scrollX = ((Long) Objects.requireNonNull(threadJs.executeScript("return window.scrollX;"))).intValue();
            scrollY = ((Long) Objects.requireNonNull(threadJs.executeScript("return window.scrollY;"))).intValue();
        } catch (Exception e) {
            logger.error("Error getting center relative to block", e);
            throw e;
        }

        double blockCenterX = blockLeft + blockWidth / 2 - scrollX;
        double blockCenterY = blockTop + blockHeight / 2 - scrollY;

        double elementCenterX = elementLeft + elementWidth / 2 - scrollX;
        double elementCenterY = elementTop + elementHeight / 2 - scrollY;

        return Math.abs(elementCenterX - blockCenterX) <= CENTER_ALIGNMENT_TOLERANCE
                && Math.abs(elementCenterY - blockCenterY) <= CENTER_ALIGNMENT_TOLERANCE;
    }

    public int getCurrentCardIndex() {
        String index = activeCard.getAttribute("data-index");
        return Integer.parseInt(index);
    }

    public int getLastCardIndex() {
        WebElement lastSlide = allCads.get(allCads.size() - 1);
        int lastIndex = Integer.parseInt(lastSlide.getAttribute("data-index"));
        return (lastIndex / 2);
    }

    public int getPreviousCardIndex() {
        int currentCardIndex = getCurrentCardIndex();
        return currentCardIndex == 0 ? getLastCardIndex() : (currentCardIndex - 1);
    }

    public void clickPreviousCard() {
        int previousCardIndex = getPreviousCardIndex();
        WebElement previousCard = allCads.get(previousCardIndex);
        clickDynamicElement(previousCard);
    }

    public int getNextCardIndex() {
        int currentCardIndex = getCurrentCardIndex();
        int lastIndex = getLastCardIndex();
        return currentCardIndex >= lastIndex ? 0 : currentCardIndex + 1;
    }

}
