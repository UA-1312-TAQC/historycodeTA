package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase128 extends TestAdminPartnerPage  {

    @Test
    @Epic("AdminPartners")
    @Issue("128")
    @Story("64")
    @Description("Verify that the system does not save a new partner if all mandatory fields are not filled " +
            "in the 'Додати партнера' modal window.")
    public void test128 () {
        SoftAssert softAssert = new SoftAssert();

        CreatePartnersModal createModal = openCreateModal();
        createModal.name.setInputField(testName);
        createModal.clickSaveButton();
        softAssert.assertTrue(createModal.isErrorConfirmationDisplayed(), "warning message is not displayed");
        createModal.clickCloseButton();

        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findPartnerByName(testName);

        softAssert.assertNull(newPartner, "New partner must not be created, but found: " + newPartner);
        softAssert.assertAll();
    }
}
