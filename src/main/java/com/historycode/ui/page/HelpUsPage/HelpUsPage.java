package com.historycode.ui.page.HelpUsPage;

import com.historycode.ui.component.HelpUs.DonatesBlockComponent;
import com.historycode.ui.component.HelpUs.PartnerModalComponent;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HelpUsPage extends BasePage {

    private final DonatesBlockComponent donatesBlockComponent;
    private final PartnerModalComponent partnerModalComponent;


    @FindBy(xpath = "//div[contains(@class, 'titleBig')]")
    private WebElement helpUsLabel;

    @FindBy(xpath = "(//button[contains(@class, 'supportButton')])[1]")
    private WebElement donateEndButton;

    @FindBy(xpath = "//div[contains(@class, 'donatesBlockContent')]")
    private WebElement donatesBlockRoot;

    @FindBy(xpath = "(//button[contains(@class, 'supportButton')])[2]")
    private WebElement becomePartnerButton;

    @FindBy(xpath = "//button[contains(@class, 'withSvg')]")
    private WebElement copyUahAccountButton;

    public HelpUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        this.donatesBlockComponent = new DonatesBlockComponent(driver, donatesBlockRoot);
        this.partnerModalComponent = new PartnerModalComponent(driver);
    }

    public String getHelpUsLabelText() {
        return helpUsLabel.getText();
    }

    public boolean isDonateEndButtonDisplayed() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollToEndOfPage();
        return donateEndButton.isDisplayed();
    }

    public void clickDonatesEndButton() {
        donateEndButton.click();
    }

    public boolean isBecomePartnerButtonDisplayed() {
        return becomePartnerButton.isDisplayed();
    }

    public void clickBecomePartnerButton() {
        scrollToEndOfPage();
        becomePartnerButton.click();
    }

    public boolean isCopyUahAccountButtonDisplayed() {
        return copyUahAccountButton.isDisplayed();
    }

    public void clickCopyUahAccountButton() {
        copyUahAccountButton.click();
    }

    public DonatesBlockComponent getDonatesBlockComponent() {
        return donatesBlockComponent;
    }


    public PartnerModalComponent getPartnerModalComponent() {
        return partnerModalComponent;
    }
}
