package com.historycode.ui.elements.adminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseInputElement extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item-label')]/label")
    protected WebElement label;

    @Getter
    @FindBy(xpath = ".//span[@class='ant-input-suffix']/span")
    protected WebElement showCountSymbols;

    public BaseInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public abstract void setInputField(String value);
    public abstract String getInputValue();
}
