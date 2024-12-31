package com.historycode.ui.elements.adminPanel;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
@Setter
public class InputElement {
    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]")
    WebElement label;

    @FindBy(xpath = "//div[contains(@class, 'input-content')]//span//input[@type='text']")
    WebElement inputField;

    @FindBy(xpath = "//span[@class='ant-input-suffix']/span")
    WebElement showCountSymbols;

    public InputElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
