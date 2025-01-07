package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.ChronologyCarousel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ChronologyComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='timeSpanContainer']")
    private WebElement timelineContainer;

    @FindBy(xpath = ".//div[@class='timelineContentContainer']")
    private WebElement cardsContainer;

    private ChronologyYearsBarComponent yearsBar;
    private ChronologyCarousel carousel;

    public ChronologyComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.yearsBar = new ChronologyYearsBarComponent(driver, timelineContainer);
        this.carousel = new ChronologyCarousel(driver, cardsContainer);
    }

    public String getTitle() {
        return title.getText();
    }

    public void selectYear(String year) {
        yearsBar.selectYear(year);
    }

    public String getSelectedYear() {
        return yearsBar.getSelectedYear();
    }

    public List<String> getAllYears() {
        return yearsBar.getAllYears();
    }

    public void nextEvent() {
        carousel.scrollToNext();
    }

    public void previousEvent() {
        carousel.scrollToPrevious();
    }
    public List<ChronologyCardComponent> getVisibleEvents() {
        return carousel.getVisibleCards();
    }

    public int getTotalEvents() {
        return carousel.getTotalCards();
    }
}
