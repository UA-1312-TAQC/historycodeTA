package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DeletePartner extends TestAdminPartnerPage {

    @BeforeMethod
    public void createPartner () {
        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.logo.uploadLogo(testLogo);
        createModal.clickSaveButton();
        createModal.clickCloseButton();
        // Verify partner creation
        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                       .getPartnersPageGridComponent()
                       .findPartnerByName(testName);
        Assert.assertNotNull(newPartner, "Partner was not created successfully");
    }

    @Test
    @Epic("AdminPartners")
    @Issue("125")
    @Story("64")
    @Description("Verify that the admin can remove partner in the 'Партнери' block")
    public void deletePartners () {
        PartnersRowComponent newPartnerBeforeDelete = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);
        newPartnerBeforeDelete.clickDelete().clickOkButton();

        PartnersRowComponent newPartnerAfterDelete = new PartnersPageAdminPanel(driver)
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);

        Assert.assertNull(newPartnerAfterDelete,
                "New partner must be deleted, but found:" + newPartnerAfterDelete);
    }
}
