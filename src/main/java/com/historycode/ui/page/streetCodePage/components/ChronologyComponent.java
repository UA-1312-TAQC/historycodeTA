package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ChronologyComponent extends BaseComponent {

    @Getter
    private ChronologyYearsBarComponent yearsBar;

    @Getter
    private ChronologyFilmCardComponent filmCardComponent;

    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = "//div[contains(@class, 'timelineYearTicksContainer')]")
    private WebElement redTimeline;

    @FindBy(xpath = ".//div[@class='timelineContentContainer']")
    private WebElement filmCardContainer;


    public ChronologyComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.yearsBar = new ChronologyYearsBarComponent(driver, redTimeline);
        this.filmCardComponent = new ChronologyFilmCardComponent(driver, filmCardContainer);
        PageFactory.initElements(driver, this);
    }

    public ChronologyComponent(WebDriver driver) {
        super(driver);
        this.yearsBar = new ChronologyYearsBarComponent(driver, redTimeline);
        this.filmCardComponent = new ChronologyFilmCardComponent(driver, filmCardContainer);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        scrollToElement(title);
        return title.getText();
    }

    public WebElement getTitleElement() {
        return title;
    }

    public WebElement getRedTimeline() {
        scrollToElement(redTimeline);
        return redTimeline;
    }
}

//    public void selectYear(String year) {
//        yearsBar.selectYear(year);
//    }
//
//    public String getSelectedYear() {
//        return yearsBar.getSelectedYear();
//    }

//    public List<String> getAllYears() {
//        return yearsBar.getAllYears();
//    }
//
//    public void nextEvent() {
//        carousel.scrollToNext();
//    }
//
//    public void previousEvent() {
//        carousel.scrollToPrevious();
//    }
//    public List<ChronologyCardComponent> getVisibleEvents() {
//        return carousel.getVisibleCards();
//    }
//
//    public int getTotalEvents() {
//        return carousel.getTotalCards();
//    }
//}
