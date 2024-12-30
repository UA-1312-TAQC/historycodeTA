package com.historycode.ui.elements.HelpUs;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CurrencyModalElement {

    private PaymentMethodElement paymentMethodElement;

    @FindBy(css = "input#uah")
    private WebElement moneyUah;

    @FindBy(css = "input#usd")
    private WebElement moneyUsd;

    @FindBy(css = "input#eur")
    private WebElement moneyEur;

    @FindBy(css = "button.button span.ccy-submit-btn")
    private WebElement ccySubmitButton;

    @FindBy(css = "button.close-modal-btn")
    private WebElement closeButton;

    public CurrencyModalElement(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public boolean isMoneyUahDisplayed() {
        return moneyUah.isDisplayed();
    }

    public void clickMoneyUah() {
        moneyUah.click();
    }

    public boolean isMoneyUsdDisplayed() {
        return moneyUsd.isDisplayed();
    }

    public void clickMoneyUsd() {
        moneyUsd.click();
    }

    public boolean isMoneyEurDisplayed() {
        return moneyEur.isDisplayed();
    }

    public void clickMoneyEur() {
        moneyEur.click();
    }

    public boolean isCcySubmitButtonDisplayed() {
        return ccySubmitButton.isDisplayed();
    }

    public void clickCcySubmitButton() {
        ccySubmitButton.click();
    }

    public boolean isCloseButtonDisplayed() {
        return closeButton.isDisplayed();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public PaymentMethodElement getPaymentMethodElement() {
        return paymentMethodElement;
    }
}
