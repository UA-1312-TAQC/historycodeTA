package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SourcesModal extends BaseModal {
    @FindBy(xpath = ".//div[contains(@class, 'sourceImgContainer')]/h1")
    private WebElement modalTitle;

    @FindBy(xpath = ".//div[contains(@class, 'mainContentContainer')]//p")
    private List<WebElement> contentItems;

    @FindBy(xpath = ".//button[@type='button' and @aria-label='Close' and contains(@class, 'ant-modal-close')]")
    private WebElement closeButton;

    public SourcesModal(WebDriver driver, WebElement rootElement) {
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

    public void closeModal() {
        closeButton.click();
    }

    public boolean isModalDisplayed() {
        return modalTitle.isDisplayed();
    }
}
