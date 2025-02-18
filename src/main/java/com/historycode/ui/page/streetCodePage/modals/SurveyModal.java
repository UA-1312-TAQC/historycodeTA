package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SurveyModal extends BaseModal {

    public SurveyModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
