package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class JobsPageGridComponent extends BaseGridComponent {
    List<JobsRowComponent> jobsRowComponents;

    public JobsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public JobsRowComponent getRowById(int id) {
        return jobsRowComponents.get(id);
    }

    public int getRowCount() {
        return jobsRowComponents.size();
    }

    public void clickNextPage() {
        pagination.clickNextPage();
    }

    public void clickPrevPage() {
        pagination.clickPrevPage();
    }

    public void clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
    }

    public void clickPrevFivePages() {
        pagination.clickPrevFivePages();
    }

    public void clickNextFivePages() {
        pagination.clickNextFivePages();
    }
}
