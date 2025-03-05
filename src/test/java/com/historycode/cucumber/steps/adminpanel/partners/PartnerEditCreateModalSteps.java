package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.page.partnerPage.PartnerPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;

public class PartnerEditCreateModalSteps {

    public static class AdminPanelPartnerPage extends BaseStep {

        private CreatePartnersModal createPartnersModal;
        private PartnersPageAdminPanel partnersPageAdminPanel = new PartnersPageAdminPanel(driver);

        @And("I click on the Створити нового партнера button")
        public void clickOnTheCreatePartnerButton() {
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
        public void clickOnTheCloseButton() {
            createPartnersModal.clickCloseButton();
        }

    }
}
