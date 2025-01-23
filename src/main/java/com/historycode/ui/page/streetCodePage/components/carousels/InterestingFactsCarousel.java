package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import com.historycode.ui.page.streetCodePage.components.InterestingFactsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[contains(@class, 'slick-slide slick-cloned')]//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//div[contains(@class, 'slick-current')]//div[@class='interestingFactSlide']")
    private WebElement factCardCurrentNode;

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

    public String getCurrentNodeTitle(){
        return factCardCurrentNode.findElement(By.xpath(".//p[@class = 'cardTextContainerTitle']")).getText();
    }
}
