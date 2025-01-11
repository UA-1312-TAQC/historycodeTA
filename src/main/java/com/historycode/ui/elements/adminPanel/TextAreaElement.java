package com.historycode.ui.elements.adminPanel;


import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextAreaElement extends BaseComponent {

    @Getter
    @FindBy(xpath = "./div[contains(@class, 'ant-form-item-label')]/label")
    private WebElement label;

    @Getter
    @FindBy(xpath = "./textarea")
    private WebElement textAreaField;

    @Getter
    @FindBy(xpath = "./span[@class='ant-input-suffix']/span")
    private WebElement charCounter;

    public TextAreaElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void setTextArea(String value) {
        waitUntilElementVisible(textAreaField);
        textAreaField.clear();
        textAreaField.sendKeys(value);
    }

    public String getTextAreaValue() {
        return textAreaField.getDomAttribute("value");
    }

    public String getCharCount() {
        return charCounter.getText();
    }
}
