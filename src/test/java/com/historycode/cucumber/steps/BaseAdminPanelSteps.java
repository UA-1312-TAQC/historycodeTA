package com.historycode.cucumber.steps;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
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

    @Step("set AccessToken")
    public void setAccessToken() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("AccessToken", provider.getAccessToken());
        localStorage.setItem("RefreshToken", provider.getRefreshToken());
    }

}
