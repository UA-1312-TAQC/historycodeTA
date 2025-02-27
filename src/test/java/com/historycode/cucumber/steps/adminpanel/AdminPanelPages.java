package com.historycode.cucumber.steps.adminpanel;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.jobspage.JobsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;

public class AdminPanelPages extends BaseStep {

    public void navigateToTab(String name) {
        AdminMenuBarComponent adminMenuBar = new BasePageAdminPanel(driver).getAdminMenuBar();
        switch (name) {
            case "History-коди" -> adminMenuBar.goToHistoryCodesPage();
            case "Новини" -> adminMenuBar.goToNewsPage();
        }
    }
}
