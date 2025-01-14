package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ChronologyFilmCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']")
    private List<WebElement> filmCards;

    @Getter
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']/text()[1]")
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

    public ChronologyFilmCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);
    }

    public ChronologyFilmCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getFilmCardByIndex(int index) {
        if (index >= 0 && index < filmCards.size()) {
            WebElement filmCard = filmCards.get(index);

            scrollToElement(filmCard);

            return filmCard;
        }
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    public void clickFilmCardByIndex(int index) {
        sleep(2000);
        WebElement card = getFilmCardByIndex(index);
        card.click();
    }

    public WebElement getFilmCardByName(String name) {
        for (int i = 0; i < filmTitles.size(); i++) {
            if (filmTitles.get(i).getText().equalsIgnoreCase(name)) {
                WebElement filmCard = filmCards.get(i);

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
}
