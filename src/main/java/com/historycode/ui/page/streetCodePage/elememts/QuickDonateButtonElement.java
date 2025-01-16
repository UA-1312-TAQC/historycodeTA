package com.historycode.ui.page.streetCodePage.elememts;

import com.historycode.ui.elements.BaseElement;
import com.historycode.ui.page.streetCodePage.modals.DonateModal;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickDonateButtonElement extends BaseElement {
    @FindBy(xpath = ".//div[@class='donateBtnCircle']")
    private WebElement donateButtonCircleNode;

    @FindBy(xpath = ".//h2[@class='donateBtnText']")
    private WebElement donateBtnTextNode;

    @FindBy(xpath = "//div[@role='dialog' and contains(@class,'donatesModal')]")
    private WebElement modalDonateRootNode;

    public QuickDonateButtonElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public DonateModal clickDonateButton() {
        clickDynamicElement(rootElement);
        return new DonateModal(driver, modalDonateRootNode);
    }

    public boolean isDonateButtonDisplayed() {
        return rootElement.isDisplayed();
    }

    public Point getButtonLocation() {
        return rootElement.getLocation();
    }

    public boolean isInViewport() {
        return (Boolean) threadJs.executeScript(
                "var elem = arguments[0];" +
                        "var rect = elem.getBoundingClientRect();" +
                        "return (" +
                        "    rect.top >= 0 &&" +
                        "    rect.left >= 0 &&" +
                        "    rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&" +
                        "    rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                        ");", rootElement);
    }
}
