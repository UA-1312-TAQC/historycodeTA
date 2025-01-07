package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtGalleryCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='slider-item-container']/div/div/img")
    private WebElement image;

    @FindBy(xpath = ".//div[@class='slider-item-container']/div/div//p[@class='imgDescription']")
    private WebElement description;

    public ArtGalleryCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getImageUrl() {
        return image.getAttribute("src");
    }

    public String getDescription() {
        return description.getText();
    }

    public void click() {
        rootElement.click();
    }
}
