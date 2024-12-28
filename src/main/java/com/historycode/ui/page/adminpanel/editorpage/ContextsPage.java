package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsPage extends BasePage {
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'partners')]")
    WebElement addNewContextsButton;

    public ContextsPage(WebDriver driver) {
        super(driver);
    }
}
