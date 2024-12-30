package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class StaticBannerComponent extends BaseComponent {

    @FindBy(css = ".banner-title")
    private WebElement titleElement;

    @FindBy(css = ".banner-description")
    private WebElement descriptionElement;

    @FindBy(css = ".banner-button")
    private WebElement bannerButton;

    public StaticBannerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }


    public String getTitle() {
        return (titleElement != null) ? titleElement.getText().trim() : "";
    }

    public String getDescription() {
        return (descriptionElement != null) ? descriptionElement.getText().trim() : "";
    }


    public String getButtonText() {
        return (bannerButton != null) ? bannerButton.getText().trim() : "";
    }


    public void clickBannerButton() {
        if (bannerButton != null) {
            bannerButton.click();
        }
    }


    public String getButtonLink() {
        return (bannerButton != null)
                ? bannerButton.getAttribute("href")
                : null;
    }
}
