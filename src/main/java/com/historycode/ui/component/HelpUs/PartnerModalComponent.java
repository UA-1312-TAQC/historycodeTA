package com.historycode.ui.component.HelpUs;

import com.historycode.ui.Base;
import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class PartnerModalComponent extends BaseComponent {


    @FindBy(css = "div.ant-modal-content")
    private WebElement partnerModal;

    @FindBy(css = "div.ant-form-item-control-input-content textarea#message")
    private WebElement inputYourIdea;

    @FindBy(css = "div.ant-form-item-control-input-content input#email")
    private WebElement inputEmail;

    @FindBy(css = "div.recaptcha-checkbox-checkmark")
    private WebElement recaptchaCheckbox;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public PartnerModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public WebElement getPartnerModal() {
        waitUntilElementVisible(partnerModal);
        return partnerModal;
    }

    public void enterIdea(String idea) {
        inputYourIdea.clear();
        inputYourIdea.sendKeys(idea);
    }

    public void enterEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void clickRecaptchaCheckbox() {
        recaptchaCheckbox.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean isModalVisible() {
        return partnerModal.isDisplayed();
    }
}
