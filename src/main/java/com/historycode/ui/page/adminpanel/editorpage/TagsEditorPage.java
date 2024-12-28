package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsEditorPage extends BaseEditorPage{
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'tags')]")
    WebElement addNewTagButton;

    public TagsEditorPage(WebDriver driver) {
        super(driver);
    }
}
