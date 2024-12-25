package com.historycode.ui.page.adminpanel.newspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseRowComponent;

public class NewsRowComponent extends BaseRowComponent {
    public NewsRowComponent(WebDriver driver, WebElement root){
        super(driver, root);
    }

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
}
