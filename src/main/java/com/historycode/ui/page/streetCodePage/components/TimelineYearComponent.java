package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimelineYearComponent extends BaseComponent {
    public TimelineYearComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void click() {
        rootElement.click();
    }

    public String getYear() {
        return rootElement.getText();
    }
}
