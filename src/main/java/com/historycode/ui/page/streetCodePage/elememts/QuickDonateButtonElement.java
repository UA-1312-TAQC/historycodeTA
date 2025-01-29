package com.historycode.ui.page.streetCodePage.elememts;

import com.historycode.ui.elements.BaseElement;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickDonateButtonElement extends BaseElement {
    @FindBy(xpath = ".//div[@class='donateBtnCircle']")
    private WebElement donateButtonCircleNode;

    @FindBy(xpath = ".//h2[@class='donateBtnText']")
    private WebElement donateBtnTextNode;

    public QuickDonateButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public DonateModal clickDonateButton() {
        clickDynamicElement(rootElement);
        return new DonateModal(driver,
                driver.findElement(By.xpath("//div[@role='dialog' and contains(@class,'donatesModal')]")));
    }

    public boolean isDonateButtonDisplayed() {
        return rootElement.isDisplayed();
    }

    public Point getButtonLocation() {
        return rootElement.getLocation();
    }

    public boolean isClickable() {
        try {
            waitUntilElementClickable(rootElement);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
