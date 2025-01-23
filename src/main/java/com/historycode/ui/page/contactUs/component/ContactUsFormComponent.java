package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactUsFormComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'formTitle')]")
    private WebElement formTitle;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'formSubTitle')]")
    private WebElement formSubTitle;

    @Getter
    @FindBy(xpath = ".//textarea[contains(@class, 'ant-input') and contains(@class, 'css-k7429z')]")
    private WebElement message;

    @Getter
    @FindBy(xpath = ".//input[contains(@class, 'ant-input') and contains(@class, 'input')]")
    private WebElement email;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'ant-btn') and contains(@class, 'ant-btn-primary')]")
    private WebElement button;

    public ContactUsFormComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void setMessage(String text) {
        message.clear();
        message.sendKeys(text);
    }

    public void setEmail(String emailAddress) {
        email.clear();
        email.sendKeys(emailAddress);
    }

    public void clickSubmitButton() {
        button.click();
    }

    public boolean isButtonEnabled() {
        return button.isEnabled();
    }
}
