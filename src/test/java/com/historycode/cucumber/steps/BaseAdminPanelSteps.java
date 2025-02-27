package com.historycode.cucumber.steps;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

import static java.lang.Thread.sleep;

public class BaseAdminPanelSteps extends BaseStep {

    protected HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    protected NewsPageAdminPanel newsPageAdminPanel;
    protected PartnersPageAdminPanel partnersPageAdminPanel;



//    @Given("User open the admin-panel page of the site and login admin")
//    public void loginWithAdmin() {
//        initDriver();
//        driver.get(provider.getBaseUIUrl());
//        setAccessToken();
//        driver.get(provider.getBaseUIUrl() + "/admin-panel");
//        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
//    }

}
