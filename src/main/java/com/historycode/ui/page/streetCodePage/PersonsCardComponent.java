package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PersonsCardComponent extends BaseComponent {
    @FindBy(xpath = "")
    private WebElement photo;

    @FindBy(xpath = "")
    private WebElement name;

    @FindBy(xpath = "")
    private WebElement position;

    @FindBy(xpath = "")
    private WebElement keywordsContainer;

    @FindBy(xpath = "")
    private List<WebElement> keywords;

    public PersonsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void click() {
        rootElement.click();
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

    public List<String> getKeywords() {
        return keywords.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
