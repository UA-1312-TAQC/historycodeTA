package com.historycode.cucumber.steps.adminpanel;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateEditNewsModalSteps extends AdminPanelPages {
    private HistoryCodesAdminPanelPage historyCodesAdminPanelPage;
    private CreateEditNewsModal createEditNewsModal;


    //@And("I click on the Створити новину button")
    public void ClickOnTheCreateNewsButton() {
     createEditNewsModal = new NewsPageAdminPanel(driver).clickAddNewInfo();
    }

    @And("I fill in the {string} field with {string}")
    public void iFillInTheFieldWith(String field, String value) {
        switch (field) {
            case "Title" -> createEditNewsModal.inputNewsTitle(value);
            case "Link" -> createEditNewsModal.inputNewsLinkTranslit(value);
            case "Text" -> createEditNewsModal.inputNewsTextEditor(value);
            case "Image" -> createEditNewsModal.clickUploadNewsPhoto(value);
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

    @And("I click on the Зберегти button")
    public void iClickOnTheSaveButton() {
        createEditNewsModal.clickSaveButton();
        sleep(1);
    }

    @Then("I should for field {string} see the error notification {string}")
    public void iShouldSeeTheErrorNotification(String field, String value) {
        switch (field) {
            case "Text" -> {
                String error = createEditNewsModal.getEditorError().getText();
                Assert.assertEquals(error, value, "text field");
            }
            case "Image" -> {
                String error = createEditNewsModal.getImageError().getText();
                Assert.assertEquals(error, value, "Image field");
            }
            case "Date" -> {
                String error = createEditNewsModal.getCreationDateError().getText();
                Assert.assertEquals(error, value, "Date field");
            }
        }
    }
}