package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class PersonCardComponent extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerTitle')]")
    private WebElement personNameNode;

    @FindBy(xpath = ".//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerSubTitle')]")
    private WebElement categoryNode;

    @FindBy(xpath = ".//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerText')]")
    private WebElement descriptionNode;

    @FindBy(xpath = ".//a[@class = 'cardTextContainerButton']")
    private WebElement moreLinkNode;

    @FindBy(xpath = ".//div[contains(@class, 'streetcodeSliderContainer')]//img")
    private WebElement personImageNode;

    public PersonCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getPersonName() {
        return personNameNode.getText().trim();
    }

    public String getCategory() {
        return categoryNode.getText().trim();
    }

    public String getDescription() {
        return descriptionNode.getText().trim();
    }

    public void clickMore() {
        moreLinkNode.click();
    }

    public String getImageSrc() {
        return personImageNode.getAttribute("src");
    }
}
