package com.historycode.ui.page.adminpanel.newspage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsRowComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement name;
    @FindBy(xpath = "")
    WebElement picture;
    @FindBy(xpath = "")
    WebElement dateOfCreation;
    @FindBy(xpath = "")
    WebElement actionDelete;
    @FindBy(xpath = "")
    WebElement actionEdit;

    public NewsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
