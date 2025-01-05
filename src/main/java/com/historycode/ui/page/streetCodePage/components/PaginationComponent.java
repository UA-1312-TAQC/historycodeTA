package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationComponent extends BaseComponent {
    private List<WebElement> paginationDots;

    public PaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public int getTotalDots() {
        return paginationDots.size();
    }
}
