package com.historycode.ui.page.adminpanel.teampage.createEditModal.photoElement;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhotoModalComponent extends BaseComponent {

    @FindBy(xpath = "(//div[@role='dialog' and @aria-modal='true'])[2]")
    private WebElement modal;

    @FindBy(xpath = "(//button[@class='close' or @aria-label='Close'])[2]")
    private WebElement closeButton;

    public PhotoModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void waitForModalToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(modal));
    }

    public void waitForModalToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(modal));
    }

    public void close() {
        closeButton.click();
        waitForModalToDisappear();
    }
}
