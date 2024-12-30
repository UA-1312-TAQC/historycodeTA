package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class NewsCardComponent extends BaseComponent {

    @FindBy(css = ".news-image")
    private WebElement newsImage;

    @FindBy(css = ".news-title")
    private WebElement title;

    @FindBy(css = ".news-date")
    private WebElement publishDate;

    @FindBy(css = ".news-summary")
    private WebElement summary;

    @FindBy(css = ".news-read-more")
    private WebElement readMoreLink;

    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }


    public String getNewsImageUrl() {
        return newsImage.getAttribute("src");
    }

    public String getTitle() {
        return title.getText().trim();
    }


    public String getPublishDate() {
        return publishDate.getText().trim();
    }

    public String getSummary() {
        return summary.getText().trim();
    }


    public void clickReadMore() {
        readMoreLink.click();
        //return new NewsPage(driver);
    }
}
