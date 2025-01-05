package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class PersonCardComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerTitle')]")
    private WebElement personName;

    @FindBy(xpath = "//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerSubTitle')]")
    private WebElement category;

    @FindBy(xpath = "//div[contains(@class, 'streetcodeSliderContainer')]//p[contains(@class, 'cardTextContainerText')]")
    private WebElement description;

    @FindBy(xpath = "//div[contains(@class, 'streetcodeSliderContainer')]//a[contains(@class, 'cardTextContainerButton')]")
    private WebElement moreLink;

    @FindBy(xpath = "//div[contains(@class, 'streetcodeSliderContainer')]//img")
    private WebElement personImage;

    public PersonCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getPersonName() {
        return personName.getText().trim();
    }

    public String getCategory() {
        return category.getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

    public void clickMore() {
        moreLink.click();
    }

    public String getImageSrc() {
        return personImage.getAttribute("src");
    }
}
