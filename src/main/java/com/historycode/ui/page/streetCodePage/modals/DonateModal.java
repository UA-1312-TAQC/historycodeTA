package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DonateModal extends BaseModal {
    @FindBy(xpath = ".//div[contains(@class, 'donatesModal')]//button[@class='ant-modal-close']")
    private WebElement closeButton;

    @FindBy(xpath = ".//div[@class = 'donatesModalContent']/h1")
    private WebElement firstTitle;

    @FindBy(xpath = ".//div[@class = 'donatesModalContent']/h3")
    private WebElement secondTitle;

    @FindBy(xpath = ".//div[@class = 'enterSum']")
    private WebElement enterSum;

    @FindBy(xpath = ".//input[contains(@class, 'amountInput')]")
    private WebElement amountInput;

    @FindBy(xpath = ".//div[contains(@class, 'amountInput')]")
    private WebElement amountInputCurrency;

    @FindBy(xpath = ".//div[@class = 'donatesBtnContainer']//button[contains(@class ,'ant-btn-default')]")
    List<WebElement> amountButtons;

    @FindBy(xpath = ".//div[@class = 'donatesInputContainer']//input[@class = 'ant-checkbox-input']")
    WebElement agreeCheckbox;

    @FindBy(xpath = ".//div[@class = 'donatesInputContainer']//label")
    WebElement agreeLabel;

    @FindBy(xpath = ".//button[@class = 'donatesDonateBtn']")
    private WebElement donateButton;

    public DonateModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void close() {
        closeButton.click();
    }

    public boolean isFirstTitleDisplayed() {
        return firstTitle.isDisplayed();
    }

    public boolean isSecondTitleDisplayed() {
        return secondTitle.isDisplayed();
    }

    public boolean isEnterSumDisplayed() {
        return enterSum.isDisplayed();
    }

    public boolean isAmountInputDisplayed() {
        return amountInput.isDisplayed();
    }

    public boolean isAmountInputListDisplayed() {
        if (amountButtons.size() == 3
                && amountButtons.get(0).isDisplayed()
                && amountButtons.get(1).isDisplayed()
                && amountButtons.get(2).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAmountInputCurrencyDisplayed() {
        return amountInputCurrency.isDisplayed();
    }

    public boolean isAgreeCheckboxDisplayed() {
        return agreeCheckbox.isDisplayed();
    }

    public boolean isAgreeLabelDisplayed() {
        return agreeLabel.isDisplayed();
    }

    public boolean isDonateButtonDisplayed() {
        return donateButton.isDisplayed();
    }

    public DonateModal clickDonateButton() {
        donateButton.click();
        return this;
    }
}