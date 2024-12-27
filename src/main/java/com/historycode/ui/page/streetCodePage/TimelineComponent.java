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

    @FindBy(xpath = ".//div[@class='timeSpanContainer']")
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
}
