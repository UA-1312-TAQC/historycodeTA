package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.TimelineCardComponent;
import com.historycode.ui.page.streetCodePage.components.TimelineYearComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TimelineComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@id='timeline']//h1")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='tickContainer ']//span")
    private List<WebElement> yearNodes;

    @FindBy(xpath = ".//div[@class='timelineItem']")
    private List<WebElement> timelineCardsNodes;

    private List<TimelineCardComponent> timelineCards;
    private List<TimelineYearComponent> timelineYears;

    public TimelineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.timelineCards = timelineCardsNodes.stream()
                .map(node -> new TimelineCardComponent(driver, node))
                .collect(Collectors.toList());
        this.timelineYears = yearNodes.stream()
                .map(node -> new TimelineYearComponent(driver, node))
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title.getText();
    }

    public void selectYear(String year) {
        timelineYears.stream()
                .filter(yearComponent -> yearComponent.getYear().equals(year))
                .findFirst()
                .ifPresent(TimelineYearComponent::click);
    }

    public List<String> getYears() {
        return timelineYears.stream()
                .map(TimelineYearComponent::getYear)
                .collect(Collectors.toList());
    }

    public List<TimelineCardComponent> getVisibleCards() {
        return timelineCards.stream()
                .filter(TimelineCardComponent::isDisplayed)
                .collect(Collectors.toList());
    }

    public int getCardsCount() {
        return timelineCards.size();
    }
}
