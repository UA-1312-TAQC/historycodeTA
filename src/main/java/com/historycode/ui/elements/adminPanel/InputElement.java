package com.historycode.ui.elements.adminPanel;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Getter;

//TODO N1 what about rootElement for input, do we need it and is it ok extend BaseComponent for it?
public class InputElement extends BaseComponent {
    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]/label")
    protected WebElement label;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'input-content')]//span//input[@type='text']")
    protected WebElement inputField;

    @Getter
    @FindBy(xpath = "//span[@class='ant-input-suffix']/span")
    protected WebElement showCountSymbols;

    public InputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void setInputField(String value) {
        inputField.clear();
        inputField.sendKeys(value);
    }

    public String getInputField() {
        return inputField.getDomAttribute("value");
    }
}
