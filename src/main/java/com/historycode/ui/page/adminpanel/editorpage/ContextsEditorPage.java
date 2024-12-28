package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsEditorPage extends BaseEditorPage{
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'partners')]")
    WebElement addNewContextsButton;

    public ContextsEditorPage(WebDriver driver) {
        super(driver);
    }
}
