package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesEditorPage extends BaseEditorPage{
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'categories')]")
    WebElement addNewCategoryButton;

    public CategoriesEditorPage(WebDriver driver) {
        super(driver);
    }
}
