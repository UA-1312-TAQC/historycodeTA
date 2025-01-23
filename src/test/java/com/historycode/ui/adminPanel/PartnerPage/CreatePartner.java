package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.page.partnerPage.PartnerPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;

import org.testng.annotations.Test;

public class CreatePartner extends TestRunnerWithAdmin  {

    String testName = "SpongeBob";
    String testDescription = "Our optimistic and energetic sponge";
    String testLogo = "logo.jpeg";
    String testMenuPageName = "Партнери";

    //TODO Maybe i have to use Dataprovider ? How do my code DRY ?
    @Test
    @Issue("130")
    @Description("Verify that the admin can add a description to a partner's card")
    public void createNotKeyPartner () {

        CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        createModal.name.setInputField(testName);
        createModal.description.setInputField(testDescription);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().clickMenuItem(testMenuPageName);

        basePage.scrollToEndOfPage();
        basePage.hoverOverPartner(testName, PartnerPage.PartnerType.NOT_KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }

    //TODO Maybe i have to use Dataprovider ? How do my code DRY ?
    @Test
    @Issue("130")
    @Description("Verify that the admin can add a description to a Key partner's card")
    public void createKeyPartner () {

        CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        createModal.keyPartner.check();
        createModal.name.setInputField(testName);
        createModal.description.setInputField(testDescription);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().clickMenuItem(testMenuPageName);

        basePage.scrollToElement(basePage.getConstantKeyPartners());
        basePage.hoverOverPartner(testName, PartnerPage.PartnerType.KEY);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }
}
