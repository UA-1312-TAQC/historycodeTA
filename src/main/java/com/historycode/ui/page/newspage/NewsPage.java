package com.historycode.ui.page.newspage;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Getter
public class NewsPage extends BasePage {
    private NewsArticleComponent newsArticleComponent;

    private RelatedNewsComponent relatedNewsComponent;
    private NavigationComponent navigationComponent;

    public NewsPage(WebDriver driver, NewsArticleComponent newsArticleComponent, RelatedNewsComponent relatedNewsComponent, NavigationComponent navigationComponent) {
        super(driver);
        this.newsArticleComponent = newsArticleComponent;
        this.relatedNewsComponent = relatedNewsComponent;
        this.navigationComponent = navigationComponent;
    }

    public String getNewsTitle() {
        return newsArticleComponent.isNewsTitleVisible();
    }

    public String getNewsDate() {
        return newsArticleComponent.isPublicationDateVisible();
    }

    public String getNewsContent() {
        return newsArticleComponent.isNewsContentVisible();
    }

    public boolean isNewsImageVisible() {
        return newsArticleComponent.isNewsImageVisible();
    }

    public boolean isPreviousButtonEnabled() {
        return navigationComponent.isPreviousNewsLinkEnabled();
    }

    public void goToPreviousPage() {
        navigationComponent.clickPreviousNewsLink();
    }

    public boolean isNextButtonEnabled() {
        return navigationComponent.isNextNewsLinkEnabled();
    }

    public void goToNextPage() {
        navigationComponent.clickNextNewsLink();
    }

    public String getRelatedNewsTitle() {
        return relatedNewsComponent.getRelatedNewsTitle();
    }

    public void clickRelatedNewsButton() {
        relatedNewsComponent.clickRelatedNewsButton();
    }

    public boolean isRelatedNewsVisible() {
        return relatedNewsComponent.isRelatedNewsVisible();
    }

    public Map<String, Boolean> checkLinksInNewsContent() {
        return newsArticleComponent.isLinkInNewsContentClickable();
    }
}
