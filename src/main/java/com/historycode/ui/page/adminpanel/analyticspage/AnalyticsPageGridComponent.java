package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsPageGridComponent extends BaseGridComponent {

    List<AnalyticsRowCompoment> analyticsRowCompoments;

    public AnalyticsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    private void initializeRowList() {
        if (analyticsRowCompoments != null)
            return;
        analyticsRowCompoments = new ArrayList<>();
        //TODO add explicit wait and selector
        List<WebElement> rowRootElements = driver.findElements(By.cssSelector(""));
        for (WebElement rootElement : rowRootElements) {
            AnalyticsRowCompoment temp = new AnalyticsRowCompoment(driver, rootElement);
            analyticsRowCompoments.add(temp);
        }
    }

    public AnalyticsRowCompoment getRowById(int id) {
        initializeRowList();
        return analyticsRowCompoments.get(id);
    }

    public int getRowCount() {
        initializeRowList();
        return analyticsRowCompoments.size();
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
}
