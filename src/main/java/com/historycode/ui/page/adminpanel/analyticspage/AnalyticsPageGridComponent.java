package com.historycode.ui.page.adminpanel.analyticspage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AnalyticsPageGridComponent extends BaseGridComponent {



    //TODO add element search
    List<AnalyticsRowCompoment> analyticsRowCompoments;
    public AnalyticsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AnalyticsRowCompoment getRowById(int id){ return analyticsRowCompoments.get(id);}

    public int getRowCount(){ return analyticsRowCompoments.size();}

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
