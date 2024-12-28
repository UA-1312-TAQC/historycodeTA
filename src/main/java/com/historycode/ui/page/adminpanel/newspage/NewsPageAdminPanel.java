package com.historycode.ui.page.adminpanel.newspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsPageAdminPanel extends BasePageAdminPanel{
    @FindBy(xpath="")
    WebElement addNewInfo;
    NewsGridComponent newsGridComponent;

    public NewsPageAdminPanel(WebDriver driver) {
        super(driver); 
    }
}
