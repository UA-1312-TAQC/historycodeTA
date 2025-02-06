package com.historycode.ui.page.newspage;

import com.historycode.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//div[@class='NewsHeader']")
    private WebElement newsHeaderRoot;

    @FindBy(xpath = "//div[@class='newsWithImageWrapper']")
    private WebElement newsTextAndImageNode;
    @FindBy(xpath = "//div[@class='newsLinks']")
    private WebElement newsLinksNode;
    @FindBy(xpath = "//div[@class='randomNewsBlock']")
    private WebElement readAlsoBlockNode;

    private NewsArticleComponent newsArticleComponent;
    private NavigationComponent navigationComponent;
    private RelatedNewsComponent relatedNewsComponent;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public NewsArticleComponent getNewsArticleComponent() {
        if (newsArticleComponent == null) {
            newsArticleComponent = new NewsArticleComponent(driver, newsTextAndImageNode);
        }
        return newsArticleComponent;
    }

    public NavigationComponent getNavigationComponent() {
        if (navigationComponent == null) {
            navigationComponent = new NavigationComponent(driver, newsLinksNode);
        }
        return navigationComponent;
    }

    public RelatedNewsComponent getRelatedNewsComponent() {
        if (relatedNewsComponent == null) {
            relatedNewsComponent = new RelatedNewsComponent(driver, readAlsoBlockNode);
        }
        return relatedNewsComponent;
    }

}
