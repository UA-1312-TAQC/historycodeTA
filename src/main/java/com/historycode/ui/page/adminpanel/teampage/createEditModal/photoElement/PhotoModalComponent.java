package com.historycode.ui.page.adminpanel.teampage.createEditModal.photoElement;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhotoModalComponent extends BaseComponent {



    @FindBy(xpath = ".//button[@class='close' or @aria-label='Close']")
    private WebElement closeButton;
    @FindBy(xpath = ".//img[@alt='uploaded']")
    private WebElement image;

    public PhotoModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void waitForModalToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(rootElement));
    }

    public void waitForModalToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(rootElement));
    }

    public void close() {
        closeButton.click();
        waitForModalToDisappear();
    }

    public String getEncodedPhoto(){
        return image.getDomAttribute("src");
    }
}
