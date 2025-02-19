package com.historycode.ui.elements.adminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class InputElement extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'ant-form-item-label')]/label")
    protected WebElement label;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'input-content')]//span//input[@type='text']")
    protected WebElement inputField;

    @Getter
    @FindBy(xpath = ".//span[@class='ant-input-suffix']/span")
    protected WebElement showCountSymbols;

    public InputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void setInputField(String value) {

        inputField.click();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            inputField.sendKeys(Keys.COMMAND, "a", Keys.BACK_SPACE);
        } else {
            inputField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        }
        inputField.sendKeys(value);

    }

    public String getInputValue() {
        return inputField.getDomAttribute("value");
    }
}
