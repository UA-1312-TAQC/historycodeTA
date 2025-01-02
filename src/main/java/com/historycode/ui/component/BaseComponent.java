package com.historycode.ui.component;

import com.historycode.ui.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseComponent extends Base {

    protected WebElement rootElement;
    private WebDriverWait threadWait;
    private JavascriptExecutor threadJs;

    public BaseComponent(WebDriver driver) {
        super(driver);
        this.rootElement = rootElement;
        this.threadWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }
    public void scrollToElement(WebElement element) {

        threadWait.until(ExpectedConditions.visibilityOf(element));
        threadJs.executeScript("arguments[0].scrollIntoView(true);", element);
        threadWait.until(ExpectedConditions.visibilityOf(element));

    }

    public void scrollToEndOfPage() {

        threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        sleep(1000);
    }
}
