package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.adminpanel.AdminPanelPages;
import com.historycode.ui.page.partnerPage.PartnerPage;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class PartnerFrontPageSteps extends AdminPanelPages {

    private PartnerPage basePage;

    @And("I am redirected to the partner block on the main page")
    public void iRedirectToPartnerBlockOnMainPage() {
        basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
    }

    @And("I see key partner with title {string} and description {string} on the page")
    public void iSeeKeyPartnerWithDescription(String name, String description) {
        basePage.scrollToElementJs(basePage.getNotKeyPartners().getFirst());
        basePage.hoverOverPartner(name, PartnerPage.PartnerType.KEY);
        Assert.assertEquals(basePage.getPopoverDescription(), description);
    }

    @And("I see not key partner with title {string} and description {string} on the page")
    public void iSeeNotPartnerWithDescription(String name, String description) {
        basePage.scrollToElementJs(basePage.getNotKeyPartners().getLast());
        basePage.hoverOverPartner(name, PartnerPage.PartnerType.NOT_KEY);
        Assert.assertEquals(basePage.getPopoverDescription(), description);
    }
}
