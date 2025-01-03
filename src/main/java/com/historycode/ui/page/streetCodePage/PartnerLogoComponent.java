package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PartnerLogoComponent extends BaseComponent {
    @FindBy(xpath = ".//img[@class='partnerLogo']")
    private WebElement logo;

    @FindBy(xpath = ".//div[@class='ant-popover partnerPopover css-k7429z css-k7429z ant-popover-placement-top']")
    private WebElement tooltip;

    @FindBy(xpath = ".//div[@class='partnerContent']//div[@class='links']//a")
    private WebElement partnerLink;

    @FindBy(xpath = ".//div[@class='partnerContent']//div[@class='description']//p")
    private WebElement partnerDescription;

    public PartnerLogoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void hover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(rootElement).perform();
    }

    public void click() {
        partnerLink.click();
    }

    public boolean isTooltipDisplayed() {
        return tooltip.isDisplayed();
    }

    public String getPartnerDescription() {
        return partnerDescription.getText();
    }

    public String getPartnerLink() {
        return partnerLink.getAttribute("href");
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
