package com.historycode.cucumber.steps.adminpanel.partners;

import com.historycode.cucumber.steps.adminpanel.AdminPanelPages;
import com.historycode.ui.page.partnerPage.PartnerPage;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class PartnerFrontPageSteps extends AdminPanelPages {
    @And("I see key partner with title {string} description {string}")
    public void iSeeKeyPartnerWithDescription(String name, String description) {
        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToElementJs(basePage.getNotKeyPartners().getFirst());
        basePage.hoverOverPartner(name, PartnerPage.PartnerType.KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), description);

    }

    @And("I see not key partner with title {string} description {string}")
    public void iSeeNotPartnerWithDescription(String name, String description) {
        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().goToPartnerPage();
        basePage.scrollToEndOfPage();
        basePage.hoverOverPartner(name, PartnerPage.PartnerType.NOT_KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), description);

    }
}
