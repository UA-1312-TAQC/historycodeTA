package com.historycode.ui.component.header;

import com.historycode.ui.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchElement extends BaseElement {

    @FindBy(xpath = "//input[contains(@class, 'ant-input')]")
    private BaseElement searchInput;

    @FindBy(xpath = "//span[contains(@class, 'ant-input-prefix')]")
    private BaseElement searchIcon;

    public SearchElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
