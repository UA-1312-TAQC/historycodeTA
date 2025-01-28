package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewsCardComponent extends BaseComponent {

    @FindBy(xpath = ".//img[contains(@class, 'newsPageImg')]")
    private WebElement newsImage;

    @FindBy(xpath = ".//p[@class='cardTextContainerTitle']")
    private WebElement title;

    @FindBy(xpath = ".//p[@class='cardTextContainerSubTitle']")
    private WebElement publishDate;

    @FindBy(xpath = ".//p[@class='cardTextContainerText']")
    private WebElement summary;
@Getter
    @FindBy(xpath = ".//a[@class='cardTextContainerButton']")
    private WebElement readMoreLink;

    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getNewsImageUrl() {
        return newsImage.getAttribute("src");}

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
        waitUntilElementVisible(readMoreLink);
        readMoreLink.click();
        //return new NewsPage(driver);
    }
}
