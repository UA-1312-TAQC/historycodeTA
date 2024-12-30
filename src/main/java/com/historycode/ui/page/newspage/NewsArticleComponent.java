package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NewsArticleComponent extends BaseComponent {
    @FindBy(xpath = "//h1")
    private WebElement newsTitle;

    @FindBy(xpath = "//div[contains(@class,'news-date')]")
    private WebElement publicationDate;

    @FindBy(xpath = "//div[contains(@class,'newsTextArea')]")
    private WebElement newsContent;

    @FindBy(xpath = "//div[contains(@class,'newsGoodImageClass Full')]")
    private WebElement newsImage;

    public NewsArticleComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String isNewsTitleVisible() {
        try {
            return newsTitle.getText();
        } catch (NoSuchElementException e) {
            return "News title is not visible";
        }
    }

    public String isPublicationDateVisible() {
        try {
            return publicationDate.getText();
        } catch (NoSuchElementException e) {
            return "News publication date is not visible";
        }
    }

    public String isNewsContentVisible() {
        try {
            return newsContent.getText();
        } catch (NoSuchElementException e) {
            return "News content is not visible";
        }
    }

    public boolean isNewsImageVisible() {
        try {
            return newsImage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
