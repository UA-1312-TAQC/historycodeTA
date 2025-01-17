package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalInputElement extends BaseComponent {

    @FindBy(xpath = ".//label[@class='ant-form-item-required']")
    private WebElement label;
    @FindBy(xpath = ".//input[contains(@class, 'ant-input')]")
    private WebElement input;

    public ModalInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getLabelString() {
        return label.getText();
    }

    public WebElement getInput() {
        return input;
    }

    public void setInput(String text) {
        input.sendKeys(text);
    }

    public WebElement getLabel() {
        return label;
    }
}
