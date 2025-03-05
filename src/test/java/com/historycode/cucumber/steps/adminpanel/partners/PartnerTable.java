package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;

public class PartnerTable extends BaseStep {

    private PartnersRowComponent newPartnerBeforeDelete;
    private SoftAssert softAssert = new SoftAssert();

    @And("I find a partner with name {string}")
    public void iFindPartnerWithNameInLastPage(String name) {
        newPartnerBeforeDelete = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(name);
    }

    @And("I click on the Видалити button for partner and confirm deletion")
    public void iClickDeleteButtonAndConfirm() {
        newPartnerBeforeDelete.clickDelete().clickOkButton();
    }

    @And("I delete partner with name {string}")
    public void deletePartnerUsingFeature(String name) {
        iFindPartnerWithNameInLastPage(name);
        iClickDeleteButtonAndConfirm();
    }

    @Then("I should not see partner with name {string} in the list")
    public void iShouldNotSeePartnerInList(String name) {
        PartnersRowComponent newPartnerAfterDelete = new PartnersPageAdminPanel(driver)
                .getPartnersPageGridComponent()
                .findPartnerByName(name);

        Assert.assertNull(newPartnerAfterDelete,
                "New partner must be deleted, but found:" + newPartnerAfterDelete);
    }

    @Then("I should see new partner with name {string} and logo {string}")
    public void iShouldSeeNewPartnerOnPartnerPage(String name, String logoSrc) {
        createdPartners.add(name);
        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(name);

        softAssert.assertEquals(newPartner.getNameText(), name);
        softAssert.assertEquals(getBase64FromFile(logoSrc), newPartner.getLogoSrc(),
                "Img code base64 don't match");
        softAssert.assertAll();
    }
}
