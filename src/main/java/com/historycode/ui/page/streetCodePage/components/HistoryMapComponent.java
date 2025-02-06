package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HistoryMapComponent extends BaseComponent {


    @FindBy(xpath = "//div[contains(@class, 'mapBlockContainer')]//div[contains(@class, 'blockHeadingTextContainer')]")
    private WebElement historyMapTitle;

    @FindBy(xpath = "(//input[@type='checkbox' and contains(@class, 'ant-checkbox-input')])[1]")
    private WebElement streetsCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox' and contains(@class, 'ant-checkbox-input')])[2]")
    private WebElement historyCodesCheckbox;

    @FindBy(xpath = "//button[contains(@class, 'infoButton')]//span[contains(@class, 'anticon-info-circle')]")
    private WebElement infoButton;
    //div[contains(@class, 'mapBlockContainer')]//div[contains(@class, 'blockHeadingTextContainer')]
    @FindBy(xpath = "//div[contains(@class, 'mapContainer')]")
    private WebElement mapContainer;

    @FindBy(xpath = "(//div[contains(@class, 'statisticsContainer')])[2]")
    private WebElement mapPopOver;

    @FindBy(xpath = "//a[contains(@class, 'leaflet-control-zoom-in')]")
    private WebElement zoomIn;

    @FindBy(xpath = "//a[contains(@class, 'leaflet-control-zoom-out')]")
    private WebElement zoomOut;

    public HistoryMapComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickStreetsCheckbox() {
        Actions actions = new Actions(driver);
        actions.moveToElement(streetsCheckbox).click().perform();
    }

    public void clickHistoryCodesCheckbox() {
        Actions actions = new Actions(driver);
        actions.moveToElement(historyCodesCheckbox).click().perform();
    }

    public void clickZoomIn() {
        zoomIn.click();
    }

    public void clickZoomOut() {
        zoomOut.click();
    }

    public void clickInfoButton() {
        infoButton.click();
    }

    public String getPopOverText() {
        return mapPopOver.getText();
    }

    public String getMapTitleText() {
        return historyMapTitle.getText();
    }

    public boolean isMapLoaded() {
        return mapContainer.isDisplayed();
    }

    public boolean isStreetsCheckboxSelected() {
        return streetsCheckbox.isSelected();
    }

    public boolean isHistoryCodesCheckboxSelected() {
        return historyCodesCheckbox.isSelected();
    }

    public boolean isPopOverDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(mapPopOver));
        return true;
    }

    public void swipeMap(int xOffset, int yOffset) {
        try {
            Actions actions = new Actions(driver);
            int startX = rootElement.getLocation().getX() + (rootElement.getSize().getWidth() / 2);
            int startY = rootElement.getLocation().getY() + (rootElement.getSize().getHeight() / 2);
            actions.moveToElement(rootElement, 0, 0)
                    .moveByOffset(startX - rootElement.getLocation().getX(), startY - rootElement.getLocation().getY())
                    .clickAndHold()
                    .moveByOffset(xOffset, yOffset)
                    .release()
                    .perform();
        } catch (Exception e) {
            System.err.println("Error during map swipe: " + e.getMessage());
        }
    }
}
