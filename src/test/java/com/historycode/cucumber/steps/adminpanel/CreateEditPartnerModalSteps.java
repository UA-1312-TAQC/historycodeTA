package com.historycode.cucumber.steps.adminpanel;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;

public class CreateEditPartnerModalSteps {

    public static class AdminPanelPartnerPage extends BaseStep {

        protected SoftAssert softAssert;
        private CreatePartnersModal createPartnersModal;
        private PartnersPageAdminPanel partnersPageAdminPanel = new PartnersPageAdminPanel(driver);

        @And("I click on the Створити нового партнера button")
        public void clickOnTheCreatePartnerButton() {
            createPartnersModal = partnersPageAdminPanel.clickAddNewPartnersButton();
        }

        @And("I fill in the {string} field with {string} for partner")
        public void iFillInTheFieldWith(String field, String value) {
            switch (field) {
                case "Title" -> createPartnersModal.name.setInputField(value);
                case "Image" -> createPartnersModal.logo.uploadLogo(value);
            }
        }

        @And("I click on the Зберегти button for partner")
        public void iClickOnTheSaveButton() {
            createPartnersModal.clickSaveButton();
            sleep(1);
        }

        @Then("I should see new partner with name {string} and logo {string}")
        public void iShouldSeeNewPartnerOnPartnerPage(String name, String logoSrc) {
            PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                    .clickLastPage()
                    .getPartnersPageGridComponent()
                    .findPartnerByName(name);

            softAssert.assertEquals(newPartner.getNameText(), name);
            softAssert.assertEquals(getBase64FromFile(logoSrc), newPartner.getLogoSrc(),
                    "Img code base64 don't match");
        }
    }
}
