package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ChronologyComponent extends BaseComponent {

    @Getter
    private final ChronologyYearsBarComponent yearsBar;

    @Getter
    private final ChronologyFilmCardComponent filmCardComponent;

    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = "//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timeSpanContainer')]")
    private WebElement years;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement greyBox;

    @FindBy(xpath = ".//div[@class='timelineContentContainer']")
    private WebElement filmCardContainer;


    public ChronologyComponent(WebDriver driver) {
        super(driver);
        this.yearsBar = new ChronologyYearsBarComponent(driver);
        this.filmCardComponent = new ChronologyFilmCardComponent(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public WebElement getTitleElement() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToElement(title);
        wait.until(ExpectedConditions.visibilityOf(title));
        return title;
    }

    public WebElement getRedTimeline() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToElement(redTimeline);
        wait.until(ExpectedConditions.visibilityOf(redTimeline));
        return redTimeline;
    }

    public WebElement getGreyBox() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToElement(greyBox);
        wait.until(ExpectedConditions.visibilityOf(greyBox));
        return greyBox;
    }

    public WebElement getFilmCardContainer() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToElement(filmCardContainer);
        wait.until(ExpectedConditions.visibilityOf(filmCardContainer));
        return filmCardContainer;
    }
}
