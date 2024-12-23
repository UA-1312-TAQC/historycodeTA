package com.historycode.ui.page.newspage;

import com.historycode.ui.Base;
import com.historycode.ui.elements.news.NavigationElement;
import com.historycode.ui.elements.news.NewsArticleElement;
import com.historycode.ui.elements.news.RelatedNewsElement;
import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class NewsPage extends BasePage {
    private NewsArticleElement newsArticleElement;

    private RelatedNewsElement relatedNewsElement;
    private NavigationElement navigationElement;

    public NewsPage(WebDriver driver, NewsArticleElement newsArticleElement, RelatedNewsElement relatedNewsElement, NavigationElement navigationElement) {
        super(driver);
        this.newsArticleElement = newsArticleElement;
        this.relatedNewsElement = relatedNewsElement;
        this.navigationElement = navigationElement;
    }
}
