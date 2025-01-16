package com.historycode.ui.page.newspage;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Getter
public class NewsPage extends BasePage {
    private final NewsArticleComponent newsArticleComponent;

    private final RelatedNewsComponent relatedNewsComponent;
    private final NavigationComponent navigationComponent;

    public NewsPage(WebDriver driver, NewsArticleComponent newsArticleComponent, RelatedNewsComponent relatedNewsComponent, NavigationComponent navigationComponent) {
        super(driver);
        this.newsArticleComponent = newsArticleComponent;
        this.relatedNewsComponent = relatedNewsComponent;
        this.navigationComponent = navigationComponent;
    }

    public String getNewsTitle() {
        return newsArticleComponent.getNewsTitle();
    }

    public String getPublicationDate() {
        return newsArticleComponent.getPublicationDate();
    }

    public String getNewsContent() {
        return newsArticleComponent.getNewsContent();
    }

    public boolean isNewsImageVisible() {
        return newsArticleComponent.isNewsImageVisible();
    }

    public List<String> checkLinksInNewsContent() {
        return newsArticleComponent.getLinksInNewsContent();
    }

    public boolean isPreviousButtonEnabled() {
        return navigationComponent.isPreviousNewsLinkEnabled();
    }

    public boolean isNextButtonEnabled() {
        return navigationComponent.isNextNewsLinkEnabled();
    }

    public String getRelatedNewsTitle() {
        return relatedNewsComponent.getRelatedNewsTitle();
    }

    public boolean isRelatedNewsVisible() {
        return relatedNewsComponent.isRelatedNewsButtonEnabled();
    }
}
