package com.historycode.ui.component.adminPanel.modalAdminPanel;

import com.historycode.ui.component.BaseModal;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteItemModal extends BaseModal {
    @FindBy(xpath = ".//div[@class='ant-modal-header']//div[@class='ant-modal-title']")
    protected WebElement title;

    @FindBy(xpath = ".//div[@class='ant-modal-body']/p")
    protected WebElement confirmationText;

    @FindBy(xpath = ".//div[@class='ant-modal-footer']/button[contains(@class, 'ant-btn-default')]")
    protected WebElement cancel;

    @FindBy(xpath = ".//button[contains(@class, 'ant-btn-primary')]")
    protected WebElement ok;

    @FindBy(xpath = ".//button[@class='ant-modal-close' and @aria-label='Close']")
    protected WebElement closeButton;

    private static final String BUTTON_XPATH_TEMPLATE = ".//div[@class='ant-modal-footer']/button[span[text()='%s']]";

    public DeleteItemModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isModalDisplayed() {
        return rootElement.isDisplayed();
    }

    public String getTitleText() {
        return title.getText().trim();
    }

    public String getConfirmationText() {
        return confirmationText.getText().trim();
    }

    public boolean containsConfirmationText(String expectedText) {
        return getConfirmationText().contains(expectedText);
    }

    @Step("Clicking Ok button in the confirmation modal window")
    public void clickOkButton() {
        waitUntilElementClickable(ok);
        ok.click();
        waitUntilElementInvisible(ok);
    }

    public void clickCancelButton() {
        cancel.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public WebElement getButtonByText(String buttonText) {
        String dynamicXpath = String.format(BUTTON_XPATH_TEMPLATE, buttonText);
        return rootElement.findElement(By.xpath(dynamicXpath));
    }
}
