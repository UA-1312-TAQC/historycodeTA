package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StreetCodeVideoComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//iframe[contains(@src,'www.youtube.com')]")
    private WebElement videoPlayer;
    @Getter
    @FindBy(xpath = "//button[contains(@class, 'ytp-large-play-button')]")
    private WebElement videoPlayButton;
    @Getter
    @FindBy(xpath = "//button[(@class = 'ytp-play-button ytp-button' and @title='Призупинити (k)')]")
    private WebElement videoPauseButton;
    public StreetCodeVideoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public boolean isVideoVisible() {
        scrollToElement(videoPlayer);
        return videoPlayer.isDisplayed();
    }

    public boolean isPlayButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[contains(@class, 'ytp-large-play-button')]")).isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }

    public boolean isPauseButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[(@class = 'ytp-play-button ytp-button' and @title='Призупинити (k)')]")).isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }

    public void clickPlayButton() {
        driver.switchTo().frame(videoPlayer);
        driver.findElement(By.xpath("//button[contains(@class, 'ytp-large-play-button')]")).click();
        driver.switchTo().defaultContent();
    }

    public void clickPauseButton() {
        driver.switchTo().frame(videoPlayer);
        driver.findElement(By.xpath("//button[(@class = 'ytp-play-button ytp-button' and @title='Призупинити (k)')]")).click();
        driver.switchTo().defaultContent();
    }

}
