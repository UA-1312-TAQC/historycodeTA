package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.DonatesBlockComponent;
import com.historycode.ui.component.FooterDonatesComponent;
import com.historycode.ui.component.PartnerModalComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HelpUsPage extends Base {
    // private WebDriver driver;

    private DonatesBlockComponent donatesBlockComponent;
    private FooterDonatesComponent footerDonatesComponent;
    private PartnerModalComponent partnerModalComponent;


    @FindBy(xpath = "//div[contains(@class, 'titleBig')]")
    private WebElement helpUsLabel;

    @FindBy(xpath = "(//button[contains(@class, 'supportButton')])[1]")
    private WebElement footerDonateButton;

    @FindBy(xpath = "(//button[contains(@class, 'supportButton')])[2]")
    private WebElement becomePartnerButton;

    @FindBy(xpath = "//button[contains(@class, 'withSvg')]")
    private WebElement copyUahAccountButton;

    public HelpUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        this.donatesBlockComponent = new DonatesBlockComponent(driver);
        this.footerDonatesComponent = new FooterDonatesComponent(driver);
        this.partnerModalComponent = new PartnerModalComponent(driver);
    }

    public String getHelpUsLabelText() {
        return helpUsLabel.getText();
    }

//    public boolean isFooterDonateButtonDisplayed() {
//        return footerDonateButton.isDisplayed();
//    }

    public void clickFooterDonateButton() {
        footerDonateButton.click();
    }

//    public boolean isBecomePartnerButtonDisplayed() {
//        return becomePartnerButton.isDisplayed();
//    }

    public void clickBecomePartnerButton() {
        becomePartnerButton.click();
    }

//    public boolean isCopyUahAccountButtonDisplayed() {
//        return copyUahAccountButton.isDisplayed();
//    }

    public void clickCopyUahAccountButton() {
        copyUahAccountButton.click();
    }

    public DonatesBlockComponent getDonatesBlockComponent() {
        return donatesBlockComponent;
    }

    public FooterDonatesComponent getFooterDonatesComponent() {
        return footerDonatesComponent;
        }

    public PartnerModalComponent getPartnerModalComponent() {
        return partnerModalComponent;
    }
}


