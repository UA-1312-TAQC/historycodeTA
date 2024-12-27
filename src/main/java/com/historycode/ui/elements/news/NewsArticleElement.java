package com.historycode.ui.elements.news;

import com.historycode.ui.elements.BaseElement;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NewsArticleElement extends BaseElement {
    @FindBy(xpath = "//h1")
    private WebElement newsTitle;

    @FindBy(xpath = "//div[contains(@class,'news-date')]")
    private WebElement publicationDate;

    @FindBy(xpath = "//div[contains(@class,'newsTextArea')]")
    private WebElement newsContent;

    public NewsArticleElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isNewsTitleVisible() {
        try {
            return newsTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPublicationDateVisible() {
        try {
            return publicationDate.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
