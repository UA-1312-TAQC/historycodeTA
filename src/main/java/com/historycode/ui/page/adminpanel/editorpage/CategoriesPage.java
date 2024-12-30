package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesPage extends BasePage {
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'categories')]")
    WebElement addNewCategoryButton;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }
}
