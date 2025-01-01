package com.historycode.ui.page.contactUs.component;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactUsFormComponent extends BaseComponent {
    @Getter @FindBy(xpath = "//div[@class='formTitle']")
    private WebElement formTitle;
    @Getter @FindBy(xpath = "//div[@class='formSubTitle']")
    private WebElement formSubTitle;
    @Getter @FindBy(xpath = "//div[@class='ant-input css-k7429z']")
    private WebElement message;
    @Getter @FindBy(xpath = "//div[@class='ant-input css-k7429z input']")
    private WebElement email;
    @Getter@FindBy(xpath = "//div[@class='ant-btn css-k7429z ant-btn-primary']")
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

    public String getFormTitle() {
        return formTitle.getText();
    }

    public String getFormSubTitle() {
        return formSubTitle.getText();
    }
}
