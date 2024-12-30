package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FactCardModal extends BaseModal {
    @FindBy(xpath = "")
    private WebElement modalImage;

    @FindBy(xpath = "")
    private WebElement modalTitle;

    @FindBy(xpath = "")
    private WebElement modalDescription;

    @FindBy(xpath = "")
    private WebElement closeButton;

    public FactCardModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getModalTitle() {
        return modalTitle.getText();
    }

    public String getModalDescription() {
        return modalDescription.getText();
    }

    public String getModalImageUrl() {
        return modalImage.getAttribute("src");
    }

    public void close() {
        closeButton.click();
    }
}
