package com.historycode.ui.page.streetCodePage.components.carousels;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class InterestingFactsCarouselSquares extends BaseCarousel{

    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li/button")
    private List<WebElement> allWowFactsSquare;
    @Getter
    @FindBy(xpath = "//div[@id ='wow-facts']//ul[contains(@class, 'slick-dots')]/li[contains(@class, 'slick-active')]/button")
    private WebElement activeWowFactsSquare;
    public InterestingFactsCarouselSquares(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public int getLastWowFactsSquareIndex() {
        return Integer.parseInt(allWowFactsSquare.get((allWowFactsSquare.size() - 1)).getText().trim());
    }

    public int getPreviousSquareIndex() {
        int currentSquareIndex = Integer.parseInt(allWowFactsSquare.get(getActiveWowFactsSquareIndex()).getText().trim());
        return currentSquareIndex == 0 ? getLastWowFactsSquareIndex() : (currentSquareIndex - 1);
    }

    public int getActiveWowFactsSquareIndex() {
        return Integer.parseInt(activeWowFactsSquare.getText().trim());
    }

    public void clickRandomWowFactsSquare() {
        Random random = new Random();
        int randomIndex = random.nextInt(allWowFactsSquare.size());
        allWowFactsSquare.get(randomIndex).click();
    }


    public int getNextWowFactsSquareIndex() {
        int activeWowFactsSquareIndex = getActiveWowFactsSquareIndex();
        int lastIndex = getLastWowFactsSquareIndex();
        return activeWowFactsSquareIndex >= lastIndex ? 0 : activeWowFactsSquareIndex + 1;
    }
}
