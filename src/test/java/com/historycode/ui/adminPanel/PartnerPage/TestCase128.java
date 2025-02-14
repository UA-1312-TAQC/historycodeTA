package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase128 extends BaseTestRunnerWithAdmin {

    String testName = "SpongeBob";

    @Test
    @Epic("AdminPartners")
    @Issue("128")
    @Story("64")
    @Description("Verify that the system doesn't save new partner without filled all mandatory fields" +
            " in the \"Додати партнера\" modal window")
    public void test128 () {
        SoftAssert softAssert = new SoftAssert();
        CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        createModal.name.setInputField(testName);

        createModal.clickSaveButton();
        softAssert.assertTrue(createModal.isErrorConfirmationDisplayed(), "warning message is not displayed");
        createModal.clickCloseButton();

        PartnersRowComponent newPartner = new PartnersPageAdminPanel(driver)
                .clickLastPage()
                .getPartnersPageGridComponent()
                .findUserByName(testName);

        softAssert.assertEquals(newPartner, null);
        softAssert.assertAll();
    }
}
