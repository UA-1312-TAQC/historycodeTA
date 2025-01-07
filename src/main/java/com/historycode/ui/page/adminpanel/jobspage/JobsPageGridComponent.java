package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class JobsPageGridComponent extends BaseGridComponent {
    @Getter
    protected List<JobsRowComponent> jobsRowComponents;

    @FindBy(xpath = "//tbody//tr")
    protected List<WebElement> rowNodes;

    public JobsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.jobsRowComponents = new ArrayList<>();
        for (WebElement row : rowNodes) {
            this.jobsRowComponents.add(new JobsRowComponent(driver, row));
        }
    }

    public JobsRowComponent getRowById(int id) {
        return jobsRowComponents.get(id);
    }

    public int getRowCount() {
        return jobsRowComponents.size();
    }

    public JobsPageGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new JobsPageGridComponent(driver, rootElement);
    }


    public JobsPageGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new JobsPageGridComponent(driver, rootElement);
    }

    public JobsPageGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new JobsPageGridComponent(driver, rootElement);
    }

    public JobsPageGridComponent clickPrevFivePages() {
        pagination.clickPrevFivePages();
        return new JobsPageGridComponent(driver, rootElement);
    }

    public JobsPageGridComponent clickNextFivePages() {
        pagination.clickNextFivePages();
        return new JobsPageGridComponent(driver, rootElement);
    }
}
