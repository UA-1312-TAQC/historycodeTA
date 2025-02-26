package com.historycode.cucumber.steps.partners;

import com.historycode.cucumber.steps.BaseAdminPanelSteps;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;


public class AdminPanelPartnerPage extends BaseAdminPanelSteps {

    protected PartnersPageAdminPanel partnersPageAdminPanel;
    protected CreatePartnersModal createPartnerModal;
    protected SoftAssert softAssert;

    @And("I click on the Створити нового партнера button")
    public void ClickOnTheCreatePartnerButton() {
        createPartnerModal = partnersPageAdminPanel.clickAddNewPartnersButton();
    }

    @And("I fill in the {string} field with {string} for partner")
    public void iFillInTheFieldWith(String field, String value) {
        switch (field) {
            case "Title" -> createPartnerModal.name.setInputField(value);
            case "Image" -> createPartnerModal.logo.uploadLogo(value);
        }
    }

    @And("I click on the Зберегти button for partner")
    public void iClickOnTheSaveButton() {
        createPartnerModal.clickSaveButton();
        sleep(1);
    }

    @Then("I should see new partner with name {String} and logo {String}")
    public void IShouldSeeNewPartnerOnPartnerPage(String name, String logoSrc) {
        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(name);

        softAssert.assertEquals(newPartner.getNameText(), name);
        softAssert.assertEquals(getBase64FromFile(logoSrc), newPartner.getLogoSrc(),
                "Img code base64 don't match");
    }
}
