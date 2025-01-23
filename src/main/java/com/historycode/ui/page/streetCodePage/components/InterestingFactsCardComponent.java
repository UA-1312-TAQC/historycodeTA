package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class InterestingFactsCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='interestingFactSlide']//img")
    private WebElement image;
    
    @Getter
    @FindBy(xpath = ".//div[@class='slick-slide slick-active slick-center slick-current']//img")
    private WebElement currentImage;
    
    @FindBy(xpath = ".//p[@class='cardTextContainerTitle']")
    private WebElement title;

    @FindBy(xpath = ".//p[@class='cardTextContainerText']")
    private WebElement description;

    @Getter
    @FindBy(xpath = ".//div[@class='description-popup ']/p")
    private WebElement hoverDescription;

    public InterestingFactsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getImageSource() {
        return image.getAttribute("src");
    }

    public void clickCard() {
        rootElement.click();
    }

    public void hoverOverImage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(currentImage).perform();
    }

    public String getHoverText() {
        return hoverDescription.getText();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
