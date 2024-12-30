package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TimelineComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='blockHeadingText']")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='timeSpanContainer']//span")
    private List<WebElement> years;

    @FindBy(xpath = "")
    private WebElement paginationNode;

    @FindBy(xpath = ".//div[@class='timelineItem']")
    private List<WebElement> timelineCardsNodes;

    private List<TimelineCardComponent> events;
    private PaginationComponent pagination;

    public TimelineComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.pagination = new PaginationComponent(driver, paginationNode);
        this.events = timelineCardsNodes.stream()
                .map(node -> new TimelineCardComponent(driver, node))
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return title.getText();
    }

    public void selectYear(String year) {
        for (int i = 0; i < years.size(); i++) {
            if (years.get(i).getText().equals(year)) {
                pagination.clickDot(i);
                break;
            }
        }
    }

    public List<String> getAllYears() {
        return years.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public TimelineCardComponent getCurrentEvent() {
        int currentIndex = pagination.getActiveDotIndex();
        return events.get(currentIndex);
    }

    public String getCurrentYear() {
        int currentIndex = pagination.getActiveDotIndex();
        return years.get(currentIndex).getText();
    }
}
