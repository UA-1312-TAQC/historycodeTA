package com.historycode.ui.component.HelpUs;


import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class DonatesBlockComponent extends BaseComponent {

    @FindBy(css = "h1")
    private WebElement donatesLabel;

    @FindBy(xpath = "//input[contains(@class, 'amountInput')]")
    private WebElement inputAmountMoney;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '₴')]]")
    private List<WebElement> donationMoneyButtons;

    @FindBy(css = "input[type='checkbox']")
    private WebElement checkbox;

    @FindBy(xpath = "//button[contains(@class, 'donatesDonateBtn')]")
    private WebElement donateButton;

    public DonatesBlockComponent(WebDriver driver, WebElement root) {
        super(driver, root);
        PageFactory.initElements(driver, this);
    }


    public WebElement getDonatesLabel() {
        scrollToElement(donatesLabel);
        return donatesLabel;
    }

    public boolean isLabelVisible() {
        return donatesLabel.isDisplayed();
    }

    public WebElement getDonateButton() {
        return donateButton;
    }

    public void clickDonationButton(String amount) {
        for (WebElement button : donationMoneyButtons) {
            if (button.getText().contains(amount)) {
                button.click();
                return;
            }
        }
        throw new NoSuchElementException("Button with amount  " + amount + " not found.");
    }

    public void enterAmount(String amount) {
        inputAmountMoney.clear();
        inputAmountMoney.sendKeys(amount);
    }

    public void clickCheckbox() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        checkbox.click();
    }

    public void clickDonateButton() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToElement(donateButton);
        donateButton.click();
    }

}
