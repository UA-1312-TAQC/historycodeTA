package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RelatedNewsComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'randomNewsTitleAndButton')]")
    private WebElement relatedNewsTitle;

    @FindBy(xpath = "//div[contains(@class,'newsButtonContainer')]")
    private WebElement relatedNewsButton;

    public RelatedNewsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getRelatedNewsTitle() {
        return relatedNewsTitle.getText();
    }

    public void clickRelatedNewsButton() {
        relatedNewsButton.click();
    }

    public boolean isRelatedNewsVisible() {
        try {
            return relatedNewsTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
