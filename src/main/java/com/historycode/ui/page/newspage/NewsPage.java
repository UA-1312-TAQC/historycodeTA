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

    public NewsPage(WebDriver driver) {
        super(driver);
    }

//    public String getPublicationDate() {
//        return newsArticleComponent.getPublicationDate();
//    }
//
//    public String getNewsContent() {
//        return newsArticleComponent.getNewsContent();
//    }
//
//    public boolean isNewsImageVisible() {
//        return newsArticleComponent.isNewsImageVisible();
//    }
//
//    public List<String> checkLinksInNewsContent() {
//        return newsArticleComponent.getLinksInNewsContent();
//    }

    public boolean isPreviousButtonEnabled() {
        return false;
    }

    public boolean isNextButtonEnabled() {
        return false;
    }

    public String getRelatedNewsTitle() {
        return "";
    }

    public boolean isRelatedNewsVisible() {
        return false;
    }

}
