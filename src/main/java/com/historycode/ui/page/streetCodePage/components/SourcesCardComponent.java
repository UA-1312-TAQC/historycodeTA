package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SourcesCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[contains(@class, 'sourcesSliderItem')]/h1")
    private WebElement categoryTitle;

    public SourcesCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return categoryTitle.getText();
    }

    public void clickCard() {
        categoryTitle.click();
    }

    public boolean isDisplayed() {
        return categoryTitle.isDisplayed();
    }
}
