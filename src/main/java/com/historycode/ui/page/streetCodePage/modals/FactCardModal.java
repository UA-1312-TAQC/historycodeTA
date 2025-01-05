package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FactCardModal extends BaseModal {
    @FindBy(xpath = ".//div[@class='factsImgContainer']//img")
    private WebElement modalImage;

    @FindBy(xpath = ".//div[@class='factsImgContainer']//p")
    private WebElement imageHoverText;

    @FindBy(xpath = ".//div[@class='factsContentContainer']/h1")
    private WebElement modalTitle;

    @FindBy(xpath = ".//div[@class='factsTextContainer']")
    private WebElement modalDescription;

    @FindBy(xpath = ".//div[@class='ant-modal css-k7429z interestingFactsModal']//button")
    private WebElement closeButton;

    public FactCardModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return modalTitle.getText();
    }

    public String getDescription() {
        return modalDescription.getText();
    }

    public String getImageSource() {
        return modalImage.getAttribute("src");
    }

    public void hoverOverImage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(modalImage).perform();
    }

    public String getImageHoverText() {
        return imageHoverText.getText();
    }

    public void closeModal() {
        closeButton.click();
    }

    public boolean isModalDisplayed() {
        return modalTitle.isDisplayed();
    }
}
