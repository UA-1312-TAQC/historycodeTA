package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase128 extends TestRunnerWithAdmin  {

    String testName = "SpongeBob";

    //TODO How to log a bug. Reason save button is enable.
    @Test
    @Issue("128")
    @Description("Verify that the system doesn't save new partner without filled all mandatory fields" +
            " in the \"Додати партнера\" modal window")
    public void test128 () {
        SoftAssert softAssert = new SoftAssert();
        CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        InputElement name = createModal.name;
        name.setInputField(testName);

        createModal.clickSaveButton();
        softAssert.assertTrue(createModal.isErrorConfirmationDisplayed());
        createModal.clickCloseButton();
        softAssert.assertAll();
    }
}
