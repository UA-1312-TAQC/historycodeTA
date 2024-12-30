package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PartnerLogoComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement logo;

    @FindBy(xpath = "")
    private WebElement tooltip;

    @FindBy(xpath = "")
    private WebElement partnerLink;

    @FindBy(xpath = "")
    private WebElement partnerDescription;

    public PartnerLogoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void hover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(logo).perform();
    }

    public String getPartnerName() {
        return logo.getAttribute("alt");
    }

    public String getTooltipText() {
        return tooltip.getText();
    }

    public void clickPartnerLink() {
        partnerLink.click();
    }

    public String getPartnerDescription() {
        return partnerDescription.getText();
    }

    public boolean isTooltipDisplayed() {
        return tooltip.isDisplayed();
    }
}
