package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class ChronologyComponent extends BaseComponent {

    @Getter
    private final ChronologyYearsBarComponent yearsBar;

    @Getter
    private final ChronologyFilmCardComponent filmCardComponent;

    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = ".//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement redTimeline;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'timeSpanContainer')]")
    private WebElement years;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement greyBox;

    @FindBy(xpath = ".//div[@class='timelineContentContainer']")
    private WebElement filmCardContainer;

    @Getter
    @FindBy(xpath = ".//div[@class=\"timelineItem\"]/../../..")
    private List<WebElement> filmCardNode;

    private  List<ChronologyFilmCardComponent> filmCardComponents;


    public ChronologyComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.yearsBar = new ChronologyYearsBarComponent(driver);
        this.filmCardComponent = new ChronologyFilmCardComponent(driver);
    }
    public  List<ChronologyFilmCardComponent> getFilmCard(){
        if (filmCardComponents == null){
            filmCardComponents = new ArrayList<>();
            for(WebElement element: filmCardNode){
                filmCardComponents.add(new ChronologyFilmCardComponent(driver, element));
            }
        }
        return filmCardComponents;
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

    public WebElement getGreyBox() {
        scrollToElement(greyBox);
        wait.until(ExpectedConditions.visibilityOf(greyBox));
        return greyBox;
    }

    public WebElement getFilmCardContainer() {
        scrollToElement(filmCardContainer);
        wait.until(ExpectedConditions.visibilityOf(filmCardContainer));
        return filmCardContainer;
    }

}
