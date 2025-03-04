package com.historycode.cucumber.steps.adminpanel;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import java.text.ParseException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.Date;
import lombok.Getter;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Getter
public class UpdateNewsModalSteps extends BaseStep {
    private final SoftAssert softAssert = new SoftAssert();

    private NewsPageAdminPanel pageAdminPanel;
    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private CreateEditNewsModal createEditNewsModal;

    @Given("User open the admin-panel page of the site and login admin")
    public void loginWithAdmin() {
        initDriver();
        driver.get(provider.getBaseUIUrl());
        setAccessToken();
        driver.get(provider.getBaseUIUrl() + "/admin-panel");
        historyCodesAdminPanelPage = new HistoryCodesAdminPanelPage(driver);
    }

    @When("I navigate to the {string} tab")
    public void navigateToNews(String name) {
        AdminMenuBarComponent adminMenuBar = new BasePageAdminPanel(driver).getAdminMenuBar();
        switch (name) {
            case "History-коди" -> historyCodesAdminPanelPage = adminMenuBar.goToHistoryCodesPage();
            case "Новини" -> pageAdminPanel = adminMenuBar.goToNewsPage();
        }
        sleep(1);
    }

    @And("I click on the Edite News")
    public void clickOnTheEditNews() {
        createEditNewsModal.inputNewsTextEditor("Updated News Text");
    }

    @And("I will change the {string} to {string}")
    public void iWillChangeTheTo(String field, String value) {

        switch (field) {
            case "Title" -> {
                createEditNewsModal.inputNewsTitle("Ukraine");
            }
            case "Link" -> {
                createEditNewsModal.inputNewsLinkTranslit("ukraine-link");
            }
            case "Text" -> {
                createEditNewsModal.inputNewsTextEditor("The brave have happiness");
            }
            case "Image" -> {
                createEditNewsModal.clickUploadNewsPhoto(value);
            }
            case "Date" -> {
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

    @And("I click on the Save button")
    public void clickOnTheSaveButton() {
        createEditNewsModal.clickSaveButton();
        sleep(1);
    }
}
    

