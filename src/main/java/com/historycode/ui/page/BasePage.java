package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.Objects;

@Getter
public abstract class BasePage extends Base {

    protected HeaderComponent header;
    protected FooterComponent footer;
    protected BurgerMenuComponent burgerMenuComponent;

    @FindBy(xpath = "//div[@id='loadingGif']")
    private WebElement loaderIcon;

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;
    @FindBy(xpath = "//div[@class='footerWrapper']")
    private WebElement footerNode;
    @FindBy(xpath = "//div[contains(@class, 'rightPartContainer')]//div[contains(@class, 'drawerContainer')]//div")
    private WebElement burgerMenu;
    @FindBy(xpath = "//div[@class='ant-drawer-body']")
    private WebElement burgerMenuBody;
    @Getter
    @FindBy(xpath = "//div[@class='headerDrawerContainer']//a[@href='/catalog']")
    private WebElement historyCodeBurgerButton;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);
        this.footer = new FooterComponent(driver, this.footerNode);
        this.burgerMenuComponent = new BurgerMenuComponent(driver, this.burgerMenu);
    }

    public boolean isBurgerMenuVisible() {
        return burgerMenu.isDisplayed();
    }

    public BurgerMenuComponent openBurgerMenu() {
        burgerMenu.click();
        return burgerMenuComponent;
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
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public Boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

}
