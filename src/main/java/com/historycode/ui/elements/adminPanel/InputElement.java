package com.historycode.ui.elements.adminPanel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class InputElement {

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]/label")
    WebElement label;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'input-content')]//span//input[@type='text']")
    WebElement inputField;

    @Getter
    @FindBy(xpath = "//span[@class='ant-input-suffix']/span")
    WebElement showCountSymbols;

    public InputElement(WebDriver driver, WebElement rootElement) {
        PageFactory.initElements(driver, this);
    }
}
