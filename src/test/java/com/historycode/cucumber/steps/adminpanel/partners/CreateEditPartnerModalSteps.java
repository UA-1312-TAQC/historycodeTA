package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.adminpanel.AdminPanelPages;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import io.cucumber.java.en.And;

public class CreateEditPartnerModalSteps extends AdminPanelPages {

    private CreatePartnersModal createPartnersModal;
    private final PartnersPageAdminPanel partnersPageAdminPanel = new PartnersPageAdminPanel(driver);

    @And("I click on the Створити нового партнера button")
    public void iClickOnTheCreatePartnerButton() {
        createPartnersModal = partnersPageAdminPanel.clickAddNewPartnersButton();
    }

    @And("I check in the keyPartner checkbox for partner")
    public void iCheckKeyPartner() {
        createPartnersModal.keyPartner.check();
    }

    @And("I fill in the {string} field with {string} for partner")
    public void iFillInTheFieldWith(String field, String value) {
        switch (field) {
            case "Title" -> createPartnersModal.name.setInputField(value);
            case "Image" -> createPartnersModal.logo.uploadLogo(value);
            case "Description" -> createPartnersModal.description.setInputField(value);
        }
    }

    @And("I click on the Зберегти button for partner")
    public void iClickOnTheSaveButton() {
        createPartnersModal.clickSaveButton();
    }

    @And("I click on the close button for partner")
    public void iClickOnTheCloseButton() {
        createPartnersModal.clickCloseButton();
        createPartnersModal = null;
    }

    @And("I create new partner with name {string} and with logo {string}")
    public void iCreatePartnerItem(String name, String logoSrc) {
        navigateToTab("Партнери");
        iClickOnTheCreatePartnerButton();
        iFillInTheFieldWith("Title", name);
        iFillInTheFieldWith("Image", logoSrc);
        iClickOnTheSaveButton();
        iClickOnTheCloseButton();
    }
}
