package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class StaticBannerComponent extends BaseComponent {

    @FindBy(xpath = ".//p[contains(@class, 'title')]")
    private WebElement titleElement;

    @FindBy(xpath = ".//p[contains(@class, 'content')]")
    private WebElement descriptionElement;

    @FindBy(xpath = ".//a[contains(@class, 'redirectButton')]")
    private WebElement bannerButton;

    public StaticBannerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return titleElement.getText().trim();
    }

    public String getDescription() {
        return descriptionElement.getText().trim();
    }

    public String getButtonText() {
        return bannerButton.getText().trim();
    }

    public String getButtonLink() {
        return bannerButton.getAttribute("href");
    }

    public void clickBannerButton() {
        bannerButton.click();
    }
}
