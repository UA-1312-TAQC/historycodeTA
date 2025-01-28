package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

@Slf4j
@Getter
public abstract class BasePage extends Base {

    protected HeaderComponent header;
    protected FooterComponent footer;


    @FindBy(xpath = "//div[@id='loadingGif']")
    private WebElement loaderIcon;

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;
    @FindBy(xpath = "//div[@class='footerWrapper']")
    private WebElement footerNode;

    @Getter
    @FindBy(xpath = "//div[@class='headerDrawerContainer']//a[@href='/catalog']")
    private WebElement historyCodeBurgerButton;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);
        this.footer = new FooterComponent(driver, this.footerNode);
    }



    public BurgerMenuComponent openBurgerMenu() {
        return header.clickBurgerMenuBtn();
    }

    public void waitForElementThenScrollUntilAllContentLoaded(WebElement elementToWaitFor) {

        waitUntilElementVisible(elementToWaitFor);

        int previousContentHeight = getContentHeight();
        long maxWaitTimeMillis = 5000;

        while (true) {
            threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            long startTime = System.currentTimeMillis();
            boolean contentHeightChanged = false;

            while (System.currentTimeMillis() - startTime < maxWaitTimeMillis) {
                int currentContentHeight = getContentHeight();

                if (currentContentHeight > previousContentHeight) {
                    previousContentHeight = currentContentHeight;
                    contentHeightChanged = true;
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (!contentHeightChanged && !isLoaderPresent()) {
                break;
            }
        }
    }

    private int getContentHeight() {
        return ((Number) Objects.requireNonNull(threadJs.executeScript("return document.body.scrollHeight;"))).intValue();
    }

    private boolean isLoaderPresent() {
        try {
            return loaderIcon.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForPageToLoad(long timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public Boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void scrollUntilElementIsVisible(WebElement element) {
        try {
            wait.until(driver -> {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
                return element.isDisplayed();
            });
            log.info("Element is now visible.");
        } catch (TimeoutException e) {
            throw new TimeoutException("Element is not visible after scrolling.", e);
        }
    }

}