package com.historycode.ui.component;

import com.historycode.ui.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseComponent extends Base {

    protected WebElement rootElement;
    protected WebDriver driver;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public BaseComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(driver),this);
    }

    public void waitUntilElementVisible(WebElement element) {
        sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
