package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DonateModal extends BaseModal {

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

    public DonateModal(WebDriver driver, WebElement rootModalElement) {
        super(driver, rootModalElement);
    }

    public boolean isFirstTitleDisplayed() {
        waitUntilElementVisible(firstTitle);
        return firstTitle.isDisplayed();
    }

    public String getTitle() {
        return firstTitle.getText();
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

    public boolean areAmountButtonsDisplayed() {
        return amountButtons.size() == 3
                && amountButtons.get(0).isDisplayed()
                && amountButtons.get(1).isDisplayed()
                && amountButtons.get(2).isDisplayed();
    }

    public boolean isAmountInputCurrencyDisplayed() {
        return amountInputCurrency.isDisplayed();
    }

    public Boolean isAgreeCheckboxDisplayed() {
        String script = """
                return arguments[0].offsetParent !== null &&
                       getComputedStyle(arguments[0]).display !== 'none' &&
                       getComputedStyle(arguments[0]).visibility !== 'hidden' &&
                       arguments[0].getBoundingClientRect().width > 0 &&
                       arguments[0].getBoundingClientRect().height > 0;
                """;
        try {
            return (Boolean) threadJs.executeScript(script, agreeCheckbox);
        } catch (NoSuchElementException e) {
            logger.error("The 'Agree' checkbox is not displayed");
            return false;
        }
    }

    public boolean isAgreeLabelDisplayed() {
        return agreeLabel.isDisplayed();
    }

    public boolean isDonateButtonDisplayed() {
        return donateButton.isDisplayed();
    }

    public boolean isDonateButtonEnabled() {
        return donateButton.isEnabled();
    }

    public DonateModal clickDonateButton() {
        donateButton.click();
        return this;
    }

    public void clickAgreeCheckbox() {
        agreeCheckbox.click();
    }

    public void clickAmountButton(int money) {
        String desiredAmount = money + "₴";
        for (WebElement amount : amountButtons) {
            if (amount.getText().equals(desiredAmount)) {
                amount.click();
                return;
            }
        }
        throw new NoSuchElementException("Amount button with text '" + desiredAmount + "' not found");
    }

}
