package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class ChronologyFilmCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "//div[@id='timeline']//div[@class='slick-track']/div")
    private List<WebElement> filmCard;

    @Getter
    @FindBy(xpath = "//div[@class='slick-list']//div[@class='timelineItem']//p[@class='timelineItemMetadata']")
    private List<WebElement> dataCards;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemMetadata']//span")
    private List<WebElement> historicalContext;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemTitle']")
    private List<WebElement> filmTitles;

    @Getter
    @FindBy(xpath = "//div[@class='slick-slide']//p[@class='timelineItemDescription']")
    private List<WebElement> description;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timelineContentContainer')]//div[contains(@class, 'slick-track')]")
    private List<WebElement> backgroundImages;

    public ChronologyFilmCardComponent(WebDriver driver) {
        super(driver);
    }


    public WebElement getYearNodeByIndex(int index) {
        if (index < 0 || index >= dataCards.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement yearNode = dataCards.get(index);
        scrollToElement(yearNode);
        elementIsVisible(yearNode);

        return yearNode;
    }

    public WebElement getFilmCardByIndex(int index) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        try {
            WebElement filmCardElement = filmCard.get(index);
            scrollToElement(filmCardElement);
            wait.until(ExpectedConditions.visibilityOf(filmCardElement));
            return filmCardElement;
        } catch (TimeoutException e) {
            System.err.println("Timeout while waiting for visibility of film card at index: " + index);
            throw e;
        }
    }

    public WebElement getFilmCardTextByIndex(int index) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        try {
            WebElement filmCardElement = filmCard.get(index);
            scrollToElement(filmCardElement);
            wait.until(ExpectedConditions.visibilityOf(filmCardElement));
            return filmCardElement;
        } catch (TimeoutException e) {
            System.err.println("Timeout while waiting for visibility of film card at index: " + index);
            throw e;
        }
    }

    public void clickFilmCardByIndex(int index) {
        try {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement card = getFilmCardByIndex(index);
            wait.until(ExpectedConditions.elementToBeClickable(card));
            threadJs.executeScript("arguments[0].click();", filmCard);
        } catch (TimeoutException e) {
            System.err.println("Card at index " + index + " is not clickable after waiting.");
            throw new IllegalStateException("Card at index " + index + " is not clickable after waiting.", e);
        }
    }

    public WebElement getFilmCardByName(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < filmTitles.size(); i++) {
            WebElement titleElement = filmTitles.get(i);
              wait.until(ExpectedConditions.visibilityOf(titleElement));
            if (titleElement.getText().equalsIgnoreCase(name)) {
                WebElement filmCard = this.filmCard.get(i);
                scrollToElement(filmCard);
                wait.until(ExpectedConditions.visibilityOf(filmCard));
                return filmCard;
            }
        }
        throw new NoSuchElementException("Film card with name '" + name + "' not found!");
    }

    public void clickFilmCardByName(String name) {
        try {
            WebElement card = getFilmCardByName(name);
            wait.until(ExpectedConditions.elementToBeClickable(card));
            card.click();
        } catch (TimeoutException e) {
            throw new IllegalStateException("Film card with name '" + name + "' is not clickable.", e);
        }
    }

    public boolean descriptionsWithinLimit(int maxLength) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < description.size(); i++) {
            WebElement descriptionElement = description.get(i);
            String text = descriptionElement.getText();
            System.out.println("Description at index " + i + " has length: " + text.length() + " characters.");

            if (text.length() > maxLength) {
                return false;
            }
        }
        return true;
    }

    public List<String> getBackgroundImageUrls() {
        return filmCard.stream()
                .map(card -> {
                    return urlFromCssValue(card.getCssValue("background-image"));
                })
                .collect(Collectors.toList());
    }

    private String urlFromCssValue(String cssValue) {
        return cssValue.replace("url(\"", "").replace("\")", "").trim();
    }

    private void elementIsVisible(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isCardCentered(int index) {

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement card = filmCard.get(index);
        wait.until(ExpectedConditions.visibilityOf(card));
        String cardClass = card.getAttribute("class");
        System.out.println("Card at index " + index + " has classes: " + cardClass);
        return cardClass.contains("slick-center");
    }

    public boolean borderColor(int index, String expectedColor) {
        if (index < 0 || index >= filmCard.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        WebElement card = filmCard.get(index);
        String borderColor = card.getCssValue("border-color");
        return expectedColor.equals(borderColor);
    }
}



