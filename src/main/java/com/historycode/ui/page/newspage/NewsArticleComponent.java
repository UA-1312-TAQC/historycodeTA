package com.historycode.ui.page.newspage;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @FindBy(xpath = "//div[contains(@class,'newsTextArea')]//a")
    private List<WebElement> linkInNewsContent;

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

    public Map<String, Boolean> isLinkInNewsContentClickable() {
        Map<String, Boolean> linkStatus = new HashMap<>();

        try {
            for (WebElement link : linkInNewsContent) {
                String url = link.getDomAttribute("href");

                if (url == null || url.isEmpty()) {
                    linkStatus.put("Invalid link / empty href", false);
                    continue;
                }

                try {
                    System.out.println("Checking link: " + url);

                    link.click();
                    waitForUrlToBe(url);

                    boolean isPageCorrect = driver.getCurrentUrl().equals(url);
                    linkStatus.put(url, isPageCorrect);

                    navigateBackToRootElement();
                } catch (Exception e) {
                    linkStatus.put(url, false);
                }
            }
        } catch (NoSuchElementException e) {
            linkStatus.put("No links found", false);
        }

        return linkStatus;
    }

    private void waitForUrlToBe(String url) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(url));
    }

    private void waitForRootElementVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(rootElement));
    }

    private void navigateBackToRootElement() {
        driver.navigate().back();
        waitForRootElementVisibility();
    }
}
