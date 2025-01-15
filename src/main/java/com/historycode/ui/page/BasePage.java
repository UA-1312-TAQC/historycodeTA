package com.historycode.ui.page;

import com.historycode.ui.Base;
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

    @FindBy(xpath = "//*[@id=loadingGif]")
    private WebElement loaderIcon;

    @FindBy(xpath = "//div[@class='HeaderBlock']")
    private WebElement headerNode;

    @FindBy(xpath = "//div[@class='footerWrapper']")
    private WebElement footerNode;

    protected HeaderComponent header;
    protected FooterComponent footer;

    public BasePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver, this.headerNode);
        this.footer = new FooterComponent(driver, this.footerNode);
    }

    public void waitForElementThenScrollUntilLoaderDisappears(WebElement elementToWaitFor) {
        waitUntilElementVisible(elementToWaitFor);
        while (true) {
            threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            if (isLoaderPresent()) {
                waitUntilElementInvisible(loaderIcon);
            } else {
                break;
            }
        }
    }

    private boolean isLoaderPresent() {
        WebElement loader = driver.findElement(By.xpath("//*[@id='loadingGif']"));
        return true;
    }

    public void waitUntilElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
