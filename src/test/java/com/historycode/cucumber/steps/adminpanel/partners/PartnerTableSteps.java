package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.adminpanel.AdminPanelPages;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.historycode.ui.utils.ImageLoader.getBase64FromFile;

public class PartnerTableSteps extends AdminPanelPages {

    private PartnersRowComponent partnerItem;
    private SoftAssert softAssert = new SoftAssert();

    @And("I find a partner with name {string}")
    public void iFindPartnerWithNameInLastPage(String name) {
        partnerItem = findPartnerWithNameInLastPage(name);
    }

    @And("I click on the Видалити button for partner and confirm deletion")
    public void iClickDeleteButtonAndConfirm() {
        partnerItem.clickDelete().clickOkButton();
    }

    @And("I delete partner with name {string}")
    public void deletePartnerUsingFeature(String name) {
        iFindPartnerWithNameInLastPage(name);
        iClickDeleteButtonAndConfirm();
    }

    @And("I return to the admin-panel partner page")
    public void iReturnToPartnerPage() {
        driver.get(provider.getBaseUIUrl() + provider.getAdminPanelPath());
        navigateToTab("Партнери");
    }

    @Then("I should not see partner with name {string} in the list")
    public void iShouldNotSeePartnerInList(String name) {
        partnerItem = findPartnerWithNameInLastPage(name);
        Assert.assertNull(partnerItem,
                "New partner must be deleted, but found:" + partnerItem);
    }

    @Then("I should see new partner with name {string} and logo {string}")
    public void iShouldSeeNewPartnerOnPartnerPage(String name, String logoSrc) {
        partnerItem = findPartnerWithNameInLastPage(name);
        softAssert.assertEquals(partnerItem.getNameText(), name);
        // Temporarily disabled due to bug #278. Needs a fix.
        softAssert.assertEquals(getBase64FromFile(logoSrc), partnerItem.getLogoSrc(),
                "Img code base64 don't match");
        softAssert.assertAll();
    }
}
