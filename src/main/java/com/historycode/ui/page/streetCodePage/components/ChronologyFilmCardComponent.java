package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChronologyFilmCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']")
    private List<WebElement> filmCard;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']")
    private List<WebElement> year;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']/span[@class='historicalContext']")
    private List<WebElement> historicalContext;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemTitle']")
    private List<WebElement> filmTitles;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemDescription']")
    private List<WebElement> description;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'slick-track')]")
    private List<WebElement> backgroundImages;

    public ChronologyFilmCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public ChronologyFilmCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getFilmCardByIndex(int index) {
        if (index >= 0 && index < filmCard.size()) {
            WebElement filmCard = this.filmCard.get(index);

            scrollToElement(filmCard);

            return filmCard;
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public void clickFilmCardByIndex(int index) {
        sleep(5000);
        WebElement card = getFilmCardByIndex(index);
        card.click();
    }

    public WebElement getFilmCardByName(String name) {
        for (int i = 0; i < filmTitles.size(); i++) {
            if (filmTitles.get(i).getText().equalsIgnoreCase(name)) {
                WebElement filmCard = this.filmCard.get(i);

                scrollToElement(filmCard);

                return filmCard;
            }
        }
        throw new NoSuchElementException("Film card with name '" + name + "' not found!");
    }

    public void clickFilmCardByName(String name) {
        WebElement card = getFilmCardByName(name);
        card.click();
    }

    public boolean allFilmCardsVisible() {
        return filmCard.stream().allMatch(WebElement::isDisplayed);
    }

    public int getFilmCardCount() {
        return filmCard.size();
    }

    public boolean filmCardsUnique() {
        Set<WebElement> uniqueCards = new HashSet<>(filmCard);
        return uniqueCards.size() == filmCard.size();
    }

    public boolean allEventsComplete() {
        for (int i = 0; i < filmCard.size(); i++) {
            boolean hasYear = year.get(i).isDisplayed() && !year.get(i).getText().isEmpty();
            boolean hasHistoricalContext = historicalContext.get(i).isDisplayed() && !historicalContext.get(i).getText().isEmpty();
            boolean hasTitle = filmTitles.get(i).isDisplayed() && !filmTitles.get(i).getText().isEmpty();
            boolean hasDescription = description.get(i).isDisplayed() && !description.get(i).getText().isEmpty();

            if (!(hasYear && hasHistoricalContext && hasTitle && hasDescription)) {
                return false;
            }
        }
        return true;
    }

    public boolean descriptionsWithinLimit(int maxLength) {
        for (WebElement description : description) {
            String text = description.getText();
            if (text.length() > maxLength) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getDescriptionLengths() {
        return description.stream()
                .map(description -> description.getText().length())
                .collect(Collectors.toList());

    }

    public List<String> getBackgroundImageUrls() {
        return filmCard.stream()
                .map(card -> extractUrlFromCssValue(card.getCssValue("background-image")))
                .collect(Collectors.toList());
    }

    private String extractUrlFromCssValue(String cssValue) {
        return cssValue.replace("url(\"", "").replace("\")", "").trim();
    }
}


