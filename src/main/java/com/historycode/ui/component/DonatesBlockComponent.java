package com.historycode.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

@Getter
public class DonatesBlockComponent {

   // private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'donatesBlockContent')]")
    private WebElement donateBlock;

    @FindBy(css = "h1")
    private WebElement donatesLabel;

    @FindBy(xpath = "//input[contains(@class, 'amountInput')]")
    private WebElement inputAmountMoney;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '₴')]]")
    private List<WebElement> donationMoneyButtons;
    /*
    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '10₴')]]" )
    private WebElement tenUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '20₴')]]")
    WebElement twentyUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '50₴')]]")
    private WebElement fiftyUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '100₴')]]")
    private WebElement oneHundredUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '200₴')]]")
    private WebElement twoHundredUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '500₴')]]" )
    private WebElement fiveHundredUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '1000₴')]]")
    private WebElement oneThousandUahButton;

    @FindBy(xpath = "//button[contains(@class, 'ant-btn') and .//span[contains(text(), '1500₴')]]")
    private WebElement oneThousandFiveHundredUahButton;
*/
    @FindBy(css = "input[type='checkbox']")
    private WebElement checkbox;

    @FindBy(xpath = "//button[contains(@class, 'donatesDonateBtn')]")
    private WebElement donateButton;

    public DonatesBlockComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDonateBlock() {
        return donateBlock;
    }

    //  public boolean isBlockVisible() {
    // return donateBlock.isDisplayed();
    // }

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
        checkbox.click();
    }

    public void clickDonateButton() {
        donateButton.click();
    }
}
