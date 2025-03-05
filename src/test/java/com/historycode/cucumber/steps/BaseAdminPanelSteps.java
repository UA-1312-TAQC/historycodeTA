package com.historycode.cucumber.steps;

import com.historycode.TestValueProvider;
import com.historycode.cucumber.contexts.ScenarioContext;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

public class BaseAdminPanelSteps extends BaseStep {

    protected WebDriver driver = ScenarioContext.getDriver();
    TestValueProvider provider = ScenarioContext.getProvider();

    @Given("I opened the admin panel and logged as admin.")
    @When("I open the admin panel and login as admin.")
    public void openAdminPanelAdmin() {
        driver.get(provider.getBaseUIUrl());
        setTokensToLocalStorage();
        driver.get(provider.getBaseUIUrl() + "/admin-panel");
    }

    @When("I click the {string} button in the left navigation panel.")
    public void clickButtonInLeftNavigationPanel(String name) {
        BasePageAdminPanel page = new BasePageAdminPanel(ScenarioContext.getDriver());
        switch (name) {
            case "History-коди" -> page.getAdminMenuBar().goToHistoryCodesPage();
            case "Партнери" -> page.getAdminMenuBar().goToPartnersPage();
            case "Едітор" -> page.getAdminMenuBar().goToEditorPage();
            case "Команда" -> page.getAdminMenuBar().goToTeamPage();
            case "Новини" -> page.getAdminMenuBar().goToNewsPage();
            case "Вакансії" -> page.getAdminMenuBar().goToJobsPage();
        }
    }

    @Step("Set tokens to local storage")
    public void setTokensToLocalStorage() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(ScenarioContext.getDriver());
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("AccessToken", ScenarioContext.getProvider().getAccessToken());
        localStorage.setItem("RefreshToken", ScenarioContext.getProvider().getRefreshToken());
    }

}
