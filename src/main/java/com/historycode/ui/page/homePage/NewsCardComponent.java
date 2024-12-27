package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsCardComponent extends BaseComponent {

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(css = ".description")
    private WebElement description;

    @FindBy(css = ".read-more")
    private WebElement readMoreLink;

    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(rootElement, this);
    }

    public String getTitle() {
        return title.getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

    public void clickReadMore() {
        readMoreLink.click();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
