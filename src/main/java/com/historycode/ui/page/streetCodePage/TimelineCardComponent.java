package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimelineCardComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement backgroundImage;

    @FindBy(xpath = "")
    private WebElement year;

    @FindBy(xpath = "")
    private WebElement historicalContext;

    @FindBy(xpath = "")
    private WebElement title;

    @FindBy(xpath = "")
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

    public String getBackgroundImageUrl() {
        return backgroundImage.getAttribute("src");
    }
}
