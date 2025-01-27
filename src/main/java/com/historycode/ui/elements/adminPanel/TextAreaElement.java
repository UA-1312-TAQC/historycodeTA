package com.historycode.ui.elements.adminPanel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextAreaElement extends BaseInputElement{

    @FindBy(xpath=".//textarea")
    protected WebElement inputField;

    public TextAreaElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Override
    public void setInputField(String value) {
        inputField.click();
        inputField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        inputField.sendKeys(value);
    }
    @Override
    public String getInputValue() {
        return inputField.getText().trim();
    }
}
