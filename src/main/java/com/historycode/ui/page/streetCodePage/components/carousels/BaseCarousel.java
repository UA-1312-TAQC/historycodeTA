package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseCarousel extends BaseComponent {
    public BaseCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    protected boolean hasArrows() {
        return false;
    }
}
