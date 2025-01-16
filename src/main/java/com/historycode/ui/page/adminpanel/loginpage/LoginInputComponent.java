package com.historycode.ui.page.adminpanel.loginpage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginInputComponent extends BaseComponent {


    @FindBy(css = "label")
    private WebElement label;

    @FindBy(css = "input")
    private WebElement inputField;


    @FindBy(css = "div.ant-form-item-explain-error")
    private WebElement errorMessage;

    public LoginInputComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getLabel() {
        return label.getText().trim();
    }

    public void fillInput(String inputString) {
        inputField.sendKeys(inputString);
    }

    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }
}
