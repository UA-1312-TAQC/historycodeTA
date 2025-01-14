package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class NewsCardComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//img[contains(@class, 'newsPageImg')]")
    private WebElement newsImage;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//p[contains(@class, 'cardTextContainerTitle')]")
    private WebElement title;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//p[contains(@class, 'cardTextContainerSubTitle')]")
    private WebElement publishDate;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//p[contains(@class, 'cardTextContainerText')]")
    private WebElement summary;

    @FindBy(xpath = "//div[contains(@class, 'newsSliderContainer')]//a[contains(@class, 'cardTextContainerButton')]")
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
