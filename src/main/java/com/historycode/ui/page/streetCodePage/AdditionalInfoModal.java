package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class AdditionalInfoModal extends BaseModal {
    @FindBy(xpath = "")
    private WebElement modalTitle;

    @FindBy(xpath = "")
    private List<WebElement> contentItems;

    @FindBy(xpath = "")
    private WebElement closeButton;

    public AdditionalInfoModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getModalTitle() {
        return modalTitle.getText();
    }

    public List<String> getContentItems() {
        return contentItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void close() {
        closeButton.click();
    }
}
