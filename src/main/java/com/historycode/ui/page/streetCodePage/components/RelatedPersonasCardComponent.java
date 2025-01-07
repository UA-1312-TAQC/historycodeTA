package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class RelatedPersonasCardComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='slider-item-container']//a[1]")
    private WebElement photo;

    @FindBy(xpath = ".//div[@class='heading']/p[1]")
    private WebElement name;

    @FindBy(xpath = ".//p[@class='aliasText']")
    private WebElement position;

    @FindBy(xpath = ".//div[@class='figureSlideText']//div[@class='relatedTagList undefined']")
    private WebElement keywordsContainer;

    @FindBy(xpath = ".//div[@class='figureSlideText']//div[@class='relatedTagList undefined']/button/p")
    private List<WebElement> streetCodeTags;

    public RelatedPersonasCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void click() {
        rootElement.click();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }

    public String getName() {
        return name.getText();
    }

    public String getPosition() {
        return position.getText();
    }

    public void hover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(rootElement).perform();
    }

    public List<String> getStreetCodeTags() {
        return streetCodeTags.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean areTagsVisible() {
        return !streetCodeTags.isEmpty() && streetCodeTags.get(0).isDisplayed();
    }
}
