package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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

    @FindBy(xpath = "//div[@class ='slick-slide slick-active slick-center slick-current']")
    private WebElement activeCard;

    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li/button")
    private List<WebElement> allWowFactsSlickSquare;
    @Getter
    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li[contains(@class, 'slick-active')]/button")
    private WebElement activeWowFactsSlickSquare;
    //TODO: remove this
    @FindBy(xpath = "//div[@id='wow-facts']//div[@class='interestingFactsContainer ']//button[@class='slick-arrow slick-next']")
    private WebElement nextButton;

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

    public int getCardCount() {
        return factCards.size();
    }

    public String getCardTitle(int index) {
        return factCards.get(index).getTitle();
    }

    public InterestingFactsCarousel clickNextButton() {
        clickDynamicElement(nextButton);
        sleep(5000);
        return this;
    }

    public String getCurrentNodeTitle() {
        return factCardCurrentNode.findElement(By.xpath(".//p[@class = 'cardTextContainerTitle']")).getText();
    }

    public int getCurrentCardIndex() {
        String index = activeCard.getAttribute("data-index");
        return Integer.parseInt(index);
    }

    public int getLastWowFactsSquareIndex() {
        return Integer.parseInt(allWowFactsSlickSquare.get((allWowFactsSlickSquare.size() - 1)).getText().trim());
    }

    public int getLastSlideIndex() {
        WebElement lastSlide = allCads.get(allCads.size() - 1);
        int lastIndex = Integer.parseInt(lastSlide.getAttribute("data-index"));
        return (lastIndex / 2);
    }

    public void clickPreviousCard() {
        int currentCardIndex = getCurrentCardIndex();
        int previousCardIndex = currentCardIndex == 0 ? getLastSlideIndex() : (currentCardIndex - 1);

        WebElement previousCard = allCads.get(previousCardIndex);
        clickDynamicElement(previousCard);
    }


    public int getActiveWowFactsSquareIndex() {
        return Integer.parseInt(activeWowFactsSlickSquare.getText().trim());
    }


    public void clickRandomWowFactsSquare() {
        Random random = new Random();
        int randomIndex = random.nextInt(allWowFactsSlickSquare.size());
        allWowFactsSlickSquare.get(randomIndex).click();
    }

}
