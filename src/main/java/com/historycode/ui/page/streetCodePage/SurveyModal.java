package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SurveyModal extends BaseModal {
    @FindBy(xpath = "")
    private WebElement closeButton;
    public SurveyModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void close() {
        closeButton.click();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }

}
