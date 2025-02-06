package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ChronologyComponent extends BaseComponent {

    private ChronologyYearsBarComponent yearsBar;

    private ChronologyFilmCardComponent filmCardComponent;

    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = ".//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'timeSpanContainer')]")
    private WebElement year;

    @FindBy(xpath = ".//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement greyBox;

    @FindBy(xpath = ".//div[@class='timelineContentContainer']")
    private WebElement filmCardContainer;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'tickContainer')]//div[contains(@class, 'timelineYearTick')]")
    private List<WebElement> yearsBoxNodes;

    @Getter
    @FindBy(xpath = ".//div[@class=\"timelineItem\"]/../../..")
    private List<WebElement> filmCardNodes;

    private List<ChronologyFilmCardComponent> filmCardComponents;

    private List<ChronologyYearsBarComponent> yearBoxComponents;

    public ChronologyComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    public WebElement getTitleElement() {
        scrollToElement(title);
        wait.until(ExpectedConditions.visibilityOf(title));
        return title;
    }

    public WebElement getRedTimeline() {
        scrollToElement(redTimeline);
        wait.until(ExpectedConditions.visibilityOf(redTimeline));
        return redTimeline;
    }

    public List<ChronologyYearsBarComponent> getYearBoxes() {
        if (yearBoxComponents == null) {
            yearBoxComponents = new ArrayList<>();
            for (WebElement element : yearsBoxNodes) {
                yearBoxComponents.add(new ChronologyYearsBarComponent(driver, element));
            }
        }
        return yearBoxComponents;
    }

    public ChronologyYearsBarComponent getYearsBar() {
        scrollToElement(year);
        if (yearsBar == null) {
            WebElement yearsBarRoot = rootElement.findElement(By.xpath(".//div[contains(@class, 'yearsBarContainer')]"));
            yearsBar = new ChronologyYearsBarComponent(driver, yearsBarRoot);
        }
        return yearsBar;
    }

    public WebElement getGreyBox() {
        scrollToElement(greyBox);
        wait.until(ExpectedConditions.visibilityOf(greyBox));
        return greyBox;
    }

    public  List<ChronologyFilmCardComponent> getFilmCard(){
        if (filmCardComponents == null){
            filmCardComponents = new ArrayList<>();
            for(WebElement element: filmCardNodes){
                filmCardComponents.add(new ChronologyFilmCardComponent(driver, element));
            }
        }
        return filmCardComponents;
    }

    public ChronologyFilmCardComponent getFilmCardComponent() {
        scrollToElement(filmCardContainer);
        if (filmCardComponent == null) {
            WebElement filmCardRoot = rootElement.findElement(By.xpath(".//div[contains(@class, 'timelineContentContainer')]"));
            filmCardComponent = new ChronologyFilmCardComponent(driver, filmCardRoot);
        }
        return filmCardComponent;
    }

    public ChronologyFilmCardComponent getFilmCardByIndex(int index) {
        List<WebElement> filmCardElements = rootElement.findElements(By.xpath(".//div[@class='timelineItem']"));

        if (index < 0 || index >= filmCardElements.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement filmCardElement = filmCardElements.get(index);
        scrollToElement(filmCardElement);
        wait.until(ExpectedConditions.visibilityOf(filmCardElement));

        return new ChronologyFilmCardComponent(driver, filmCardElement);
    }

    public void clickFilmCardByIndex(int index) {
        ChronologyFilmCardComponent filmCard = getFilmCardByIndex(index);

        if (filmCard != null) {
            filmCard.clickFilmCard();
            System.out.println("Successfully clicked on film card at index: " + index);
        } else {
            throw new NoSuchElementException("Film card at index " + index + " not found.");
        }
    }

    public void clickYearBoxByIndex(int index) {
        List<WebElement> yearBoxes = rootElement.findElements(By.xpath(".//div[contains(@class, 'timelineYearTick')]"));
        if (index < 0 || index >= yearBoxes.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement yearBox = yearBoxes.get(index);
        scrollToElement(yearBox);
        wait.until(ExpectedConditions.elementToBeClickable(yearBox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yearBox);
    }

    public void getYearNodeByIndex(int index) {
        List<WebElement> yearNodes = rootElement.findElements(By.xpath(".//div[contains(@class, 'timeSpanContainer')]//span"));
        if (index < 0 || index >= yearNodes.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        WebElement yearNode = yearNodes.get(index);
        scrollToElement(yearNode);
        wait.until(ExpectedConditions.visibilityOf(yearNode));
        yearNode.getText();
    }
}
