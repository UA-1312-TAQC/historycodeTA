package com.historycode.ui.page.adminpanel.newspage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;

@Getter
public class NewsPageGridComponent extends BaseGridComponent {
    public NewsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Getter
    List<NewsRowComponent> newsRowComponents;

    public List<NewsRowComponent> updateNewsRows(WebDriver driver) {

        List<WebElement> gridRows = driver.findElements(By.xpath("//tbody//tr"));

        for (WebElement rootElement : gridRows) {
            newsRowComponents.add(new NewsRowComponent(driver, rootElement));
        }
        return newsRowComponents;
    }

    public NewsRowComponent getRowById(int id) {
        return newsRowComponents.get(id);
    }

    public int getRowCount() {
        return newsRowComponents.size();
    }
    
    public void clickNextPage() {
        pagination.clickNextPage();
        this.newsRowComponents = updateNewsRows(driver);
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
        this.newsRowComponents = updateNewsRows(driver);
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        this.newsRowComponents = updateNewsRows(driver);
    }

    public void clickNextFivePages() {
        pagination.clickNextFivePages();
        this.newsRowComponents = updateNewsRows(driver);
    }

    public void clickPrevFivePages() {
        pagination.clickPrevFivePages();
        this.newsRowComponents = updateNewsRows(driver);
    }
}
