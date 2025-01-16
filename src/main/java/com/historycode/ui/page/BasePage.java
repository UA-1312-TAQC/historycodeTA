package com.historycode.ui.page;

import com.historycode.ui.Base;
import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.component.footer.FooterComponent;
import com.historycode.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public abstract class BasePage extends Base {

    protected HeaderComponent header;
    protected FooterComponent footer;
    protected BurgerMenuComponent burgerMenuComponent;
    @FindBy(xpath = "//*[@id='loadingGif']")
    private WebElement loaderIcon;

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;
    @FindBy(xpath = "//div[@class='footerWrapper']")
    private WebElement footerNode;
    @FindBy(xpath = "//div[contains(@class, 'rightPartContainer')]//div[contains(@class, 'drawerContainer')]//div")
    private WebElement burgerMenu;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);
        this.footer = new FooterComponent(driver, this.footerNode);
        this.burgerMenuComponent = new BurgerMenuComponent(driver, this.burgerMenu);
    }

    public boolean isBurgerMenuVisible() {
        sleep(5000);
        return burgerMenu.isDisplayed();
    }

    public void openBurgerMenu() {
        sleep(5000);
        burgerMenu.click();
    }

//    public void waitForElementThenScrollUntilLoaderDisappears(WebElement elementToWaitFor) {
//        waitUntilElementVisible(elementToWaitFor);
//        while (true) {
//            threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            if (isLoaderPresent()) {
//                waitUntilElementInvisible(loaderIcon);
//            } else {
//                break;
//            }
//        }
//    }


    public void waitForElementThenScrollUntilLoaderDisappears(WebElement elementToWaitFor) {
        waitUntilElementVisible(elementToWaitFor);
        long startTime = System.currentTimeMillis();
        long timeout = 10 * 1000L;
        while (true) {

            try {
                threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            } catch (Exception e) {
//                logger.error("Failed to scroll: " + e.getMessage());
                break;
            }
            if (isLoaderPresent()) {
                waitUntilElementInvisible(loaderIcon);
            } else {
                break;
            }
            if (System.currentTimeMillis() - startTime > timeout) {
//                logger.warn("Timeout waiting for loader to disappear");
                break;
            }
        }
    }

    private boolean isLoaderPresent() {
        try {
            return loaderIcon.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
