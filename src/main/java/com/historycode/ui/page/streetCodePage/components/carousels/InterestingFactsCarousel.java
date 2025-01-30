package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[contains(@class, 'slick-slide slick-cloned')]//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = "//div[@id='wow-facts']//div[contains(@class, 'slick-slide')]")
    List<WebElement> allCads;
    @FindBy(xpath = ".//div[@class='interestingFactSlide']//div[contains(@class, 'slick-current')]")
    private WebElement factCardCurrentNode;

    @FindBy(xpath = ".//div[contains(@class, 'oneFactItem')]//div[@class='interestingFactSlide']")
    private WebElement oneCardNode;

    @FindBy(xpath = "//div[@class ='slick-slide slick-active slick-center slick-current']")
    private WebElement activeCard;

    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li/button")
    private List<WebElement> allWowFactsSlickSquare;
    @Getter
    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li[contains(@class, 'slick-active')]/button")
    private WebElement activeWowFactsSlickSquare;
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
        return (oneCardNode != null) && (oneCardNode.isDisplayed());
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
    public String getCurrentNodeTitle() {
        waitUntilElementVisible(factCardCurrentNode);
        return factCardCurrentNode.findElement(By.xpath(".//p[@class = 'cardTextContainerTitle']")).getText();
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

    public int getLastWowFactsSquareIndex() {
        return Integer.parseInt(allWowFactsSlickSquare.get((allWowFactsSlickSquare.size() - 1)).getText().trim());
    }

    public int getPreviousSquareIndex() {
        int currentSquareIndex = Integer.parseInt(allWowFactsSlickSquare.get(getCurrentCardIndex()).getText().trim());
        return currentSquareIndex == 0 ? getLastWowFactsSquareIndex() : (currentSquareIndex - 1);
    }

    public int getActiveWowFactsSquareIndex() {
        return Integer.parseInt(activeWowFactsSlickSquare.getText().trim());
    }

    public void clickRandomWowFactsSquare() {
        Random random = new Random();
        int randomIndex = random.nextInt(allWowFactsSlickSquare.size());
        allWowFactsSlickSquare.get(randomIndex).click();
    }

    public int getNextCardIndex() {
        int currentCardIndex = getCurrentCardIndex();
        return currentCardIndex + 1;
    }

    public int getNextWowFactsSquareIndex() {
        int activeWowFactsSquareIndex = getActiveWowFactsSquareIndex();
        return activeWowFactsSquareIndex + 1;
    }

}
