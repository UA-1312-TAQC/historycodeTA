package com.historycode.ui.page.adminpanel.historycodePage.component;


import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamPageGridComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryCodesAdminPanelGridComponent extends BaseGridComponent {

    protected List<HistoryCodesAdminPanelRowComponent> historyCodesRows;
    @Getter @FindBy(xpath = "./tbody//tr")
    protected List<WebElement> historyCodesRowNodes;

    public HistoryCodesAdminPanelGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        for(WebElement element: historyCodesRowNodes){
            historyCodesRows.add(new HistoryCodesAdminPanelRowComponent(driver, element));
        }
    }

    public int getRowsCount() {
        return historyCodesRows.size();
    }

    public HistoryCodesAdminPanelGridComponent clickNextPage() {
        pagination.clickNextPage();
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }

    public HistoryCodesAdminPanelGridComponent clickPrevPage() {
        pagination.clickPrevPage();
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }

    public HistoryCodesAdminPanelGridComponent clickPaginationItem(int index) {
        pagination.clickPaginationItem(index);
        return new HistoryCodesAdminPanelGridComponent(driver, rootElement);
    }
}
