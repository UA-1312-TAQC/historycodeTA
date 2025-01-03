package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimelineCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']/text()[1]")
    private WebElement year;

    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemMetadata']/span[@class='historicalContext']")
    private WebElement historicalContext;

    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemTitle']")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='timelineItem']//p[@class='timelineItemDescription']")
    private WebElement description;

    public TimelineCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getYear() {
        return year.getText();
    }

    public String getHistoricalContext() {
        return historicalContext.getText();
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
