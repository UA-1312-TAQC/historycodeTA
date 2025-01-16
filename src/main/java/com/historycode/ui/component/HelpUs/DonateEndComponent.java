package com.historycode.ui.component.HelpUs;


import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.elements.HelpUs.CurrencyModalElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DonateEndComponent extends BaseComponent {

    private final CurrencyModalElement currencyModalElement;

    @FindBy(css = "h2.merchant-name")
    private WebElement donatesLabel;

    @FindBy(css = "div.money-input-contenteditable")
    private WebElement amountMoney;

    @FindBy(css = "button.currency-btn")
    private WebElement currencyButton;

    @FindBy(css = "button.button div.inline-centered")
    private WebElement continueButton;


    public DonateEndComponent(WebDriver driver, WebElement root) {
        super(driver, root);

        this.currencyModalElement = new CurrencyModalElement(driver);
    }

    public String getDonatesLabelText() {
        return donatesLabel.getText();
    }

    public boolean isAmountMoneyDisplayed() {
        return amountMoney.isDisplayed();
    }

    public void enterAmountMoney(String amount) {
        amountMoney.clear();
        amountMoney.sendKeys(amount);
    }

    public boolean isCurrencyButtonDisplayed() {
        return currencyButton.isDisplayed();
    }

    public void clickCurrencyButton() {
        currencyButton.click();
    }

    public boolean isContinueButtonDisplayed() {
        return continueButton.isDisplayed();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public CurrencyModalElement getCurrencyModalElement() {
        return currencyModalElement;
    }
}
