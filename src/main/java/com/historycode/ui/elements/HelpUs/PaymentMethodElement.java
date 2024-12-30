package com.historycode.ui.elements.HelpUs;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class PaymentMethodElement {

    @FindBy(css = "div.main-layout--merchant-name")
    private WebElement merchantLabel;

    @FindBy(css = "div.qr-block--img")
    private WebElement qrImage;

    @FindBy(xpath = "//a[contains(@href, 'https://monobank.ua/get')]")
    private WebElement payMonoBank;

    @FindBy(xpath = "//div[contains(@id, 'pay-buttons')]//div[contains(@class, 'tap-to-pay-btn-wrapper')]//apple-pay-button")
    private WebElement applePayButton;

    @FindBy(css = "button#gpay-button-online-api-id")
    private WebElement googlePayButton;

    @FindBy(css = "button#pay-by-card")
    private WebElement choosePayCardButton;

    @FindBy(css = "input#cardNumber")
    private WebElement inputCardNumber;

    @FindBy(css = "input#expiresAt")
    private WebElement inputValidityPeriod;

    @FindBy(css = "input#cvc")
    private WebElement inputCvc;

    @FindBy(css = "button#payWithCardButton")
    private WebElement payCardButton;

    @FindBy(css = "button#pay-by-installment")
    private WebElement choosePayInstallmentButton;

    @FindBy(css = "div.installment-block button.main-layout--button")
    private WebElement payInstallmentButton;


    public PaymentMethodElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isMerchantLabelDisplayed() {
        return merchantLabel.isDisplayed();
    }

    public boolean isQrImageDisplayed() {
        return qrImage.isDisplayed();
    }

    public boolean isPayMonoBankDisplayed() {
        return payMonoBank.isDisplayed();
    }

    public void clickPayMonoBank() {
        payMonoBank.click();
    }

    public boolean isApplePayButtonDisplayed() {
        return applePayButton.isDisplayed();
    }

    public void clickApplePayButton() {
        applePayButton.click();
    }

    public boolean isGooglePayButtonDisplayed() {
        return googlePayButton.isDisplayed();
    }

    public void clickGooglePayButton() {
        googlePayButton.click();
    }

    public boolean isChoosePayCardButtonDisplayed() {
        return choosePayCardButton.isDisplayed();
    }

    public void clickChoosePayCardButton() {
        choosePayCardButton.click();
    }

    public void inputCardNumber(String number) {
        inputCardNumber.clear();
        inputCardNumber.sendKeys(number);
    }

    public void inputCvc(String number) {
        inputCvc.clear();
        inputCvc.sendKeys(number);
    }

    public boolean isPayCardButtonDisplayed() {
        return payCardButton.isDisplayed();
    }

    public void clickPayCardButton() {
        payCardButton.click();
    }

    public boolean isChoosePayInstallmentButtonDisplayed() {
        return choosePayInstallmentButton.isDisplayed();
    }

    public void clickChoosePayInstallmentButton() {
        choosePayInstallmentButton.click();
    }

    public boolean isPayInstallmentButtonDisplayed() {
        return payInstallmentButton.isDisplayed();
    }

    public void clickPayInstallmentButton() {
        payInstallmentButton.click();
    }
}
