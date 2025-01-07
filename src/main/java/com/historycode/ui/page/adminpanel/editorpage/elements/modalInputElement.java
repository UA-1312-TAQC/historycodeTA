package com.historycode.ui.page.adminpanel.editorpage.elements;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class modalInputElement extends BaseComponent {
    public static final String LABEL_XPATH = ".//label[@class='ant-form-item-required']";
    public static final String INPUT_XPATH = ".//input[contains(@class, 'ant-input')]";

    @FindBy(xpath = LABEL_XPATH)
    private WebElement label;
    @FindBy(xpath = INPUT_XPATH)
    private WebElement input;

    public modalInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getLabelString() {
        return label.getText();
    }

    public WebElement getInput() {
        return input;
    }

    public void setInput(String text) {
        BasePage.moveToElement(driver, input);
        input.sendKeys(text);
    }

    public WebElement getLabel() {
        return label;
    }
}
