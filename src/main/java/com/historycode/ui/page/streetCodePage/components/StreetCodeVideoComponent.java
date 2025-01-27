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
    @FindBy(xpath = "//button[(@class = 'ytp-play-button ytp-button' and @data-title-no-tooltip = 'Пауза')]")
    private WebElement videoPauseButton;
    public StreetCodeVideoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public boolean isVideoVisible() {
        return videoPlayer.isDisplayed();
    }

    public boolean isPlayButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[contains(@class, 'ytp-large-play-button')]")).isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }

    public boolean isActionPauseButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[(@class = 'ytp-play-button ytp-button' and @data-title-no-tooltip = 'Пауза')]")).isDisplayed();
        driver.switchTo().defaultContent();
        return result;
    }
    public boolean isActionPlayButtonVisible() {
        driver.switchTo().frame(videoPlayer);
        boolean result = driver.findElement(By.xpath("//button[(@class = 'ytp-play-button ytp-button' and @data-title-no-tooltip = 'Смотреть')]")).isDisplayed();
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
        WebElement button = driver.findElement(By.xpath("//button[(@class = 'ytp-play-button ytp-button' and @data-title-no-tooltip = 'Пауза')]"));
        clickDynamicElement(button);
        driver.switchTo().defaultContent();
    }
}
