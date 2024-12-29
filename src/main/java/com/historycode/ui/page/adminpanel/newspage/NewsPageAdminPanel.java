package com.historycode.ui.page.adminpanel.newspage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewsPageAdminPanel extends BasePageAdminPanel{
    @FindBy(xpath="//button[span[text()='Створити новину']]")
    WebElement addNewInfo;
    NewsPageGridComponent newsGridComponent;

    public NewsPageAdminPanel(WebDriver driver) {
        super(driver); 
        newsGridComponent = new NewsPageGridComponent(driver, driver.findElement(By.className("partners-page-container")));
    }

    public NewsPageGridComponent getNewsPageGridComponent() {
        return newsGridComponent;
    }
}

