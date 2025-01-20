package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.Keys;
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

    public String getInputText() {
        return input.getDomProperty("value");
    }

    public void clearInput() {
        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
    }

    public void setInput(String text) {
        input.sendKeys(text);
    }

    public WebElement getLabel() {
        return label;
    }
}
