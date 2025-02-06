package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class NewsArticleComponent extends BaseComponent {
    @FindBy(xpath = "//h1")
    private WebElement newsTitle;

    @FindBy(xpath = "//div[contains(@class,'news-date')]")
    private WebElement publicationDate;

    @FindBy(xpath = "//div[contains(@class,'newsTextArea')]")
    private WebElement newsContent;

    @FindBy(xpath = "//div[contains(@class,'newsGoodImageClass Full')]")
    private WebElement newsImage;

    @FindBy(xpath = "//div[contains(@class,'newsTextArea')]//a")
    private List<WebElement> linkInNewsContent;

    public NewsArticleComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getNewsTitle() {
        waitUntilElementVisible(newsTitle);
        return newsTitle.getText();
    }

    public String getPublicationDate() {
        waitUntilElementVisible(publicationDate);
        return publicationDate.getText();
    }

    public String getNewsContent() {
        waitUntilElementVisible(newsContent);
        return newsContent.getText();
    }

    public boolean isNewsImageVisible() {
        return newsImage.isDisplayed();
    }

    public List<String> getLinksInNewsContent() {
        List<String> links = new ArrayList<>();

        for (WebElement link : linkInNewsContent) {
            String url = link.getDomAttribute("href");
            if (url != null && !url.isEmpty()) {
                links.add(url);
            }
        }
        return links;
    }
}
