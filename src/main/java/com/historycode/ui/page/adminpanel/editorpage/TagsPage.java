package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagsPage extends BasePage {
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'tags')]")
    WebElement addNewTagButton;

    public TagsPage(WebDriver driver) {
        super(driver);
    }
}
