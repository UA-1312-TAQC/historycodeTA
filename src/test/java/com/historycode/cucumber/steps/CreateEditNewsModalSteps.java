package com.historycode.cucumber.steps;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class CreateEditNewsModalSteps extends NewsPageSteps {

    @Given("User open the admin-panel page of the site and login admin")
    public void loginWithAdmin() {
        initDriver();
        driver.get(provider.getBaseUIUrl());
        setAccessToken();
        driver.get(provider.getBaseUIUrl()+ "/admin-panel");
        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
    }

    @When("I navigate to the {string} tab")
    public void navigateToTab(String name) {
        AdminMenuBarComponent adminMenuBar= new BasePageAdminPanel(driver).getAdminMenuBar();
        switch (name) {
            case "History-коди" -> historyCodesAdminPanelPage = adminMenuBar.goToHistoryCodesPage();
            case "Новини"-> newsPageAdminPanel = adminMenuBar.goToNewsPage();
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @When("I click on the Створити новину button")
    public void ClickOnTheCreateNewsButton() {
        newsPageAdminPanel.clickAddNewInfo();
    }

    @And("I fill in the {string} field with {string}")
    public void iFillInTheFieldWith(String field, String value) {
        switch (field) {
            case "Title" -> createEditNewsModal.inputNewsTitle(value);
            case "Link" -> createEditNewsModal.inputNewsLinkTranslit(value);
            case "Text" -> createEditNewsModal.inputNewsTextEditor(value);
            case "Image" -> createEditNewsModal.clickUploadNewsPhoto(value);
            case "Date"-> {
                Date date;

                if (value.equals("current date")) {
                    date = new Date(System.currentTimeMillis());
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

                    try {
                        date = formatter.parse(value);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

                createEditNewsModal.inputNewsCreationDate(date);
            }
        }
    }
}
