package com.historycode.cucumber.steps.adminpanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class NewsPageSteps extends AdminPanelPages {
    protected NewsPageAdminPanel pageAdminPanel;

    protected NewsPageAdminPanel getPage(){
        return new NewsPageAdminPanel(driver);
    }

    /*
    @When("I navigate to the {string} tab")
    public void iNavigateToTab(String name) {
        navigateToTab(name);
        sleep(1);

    }

     */


}
