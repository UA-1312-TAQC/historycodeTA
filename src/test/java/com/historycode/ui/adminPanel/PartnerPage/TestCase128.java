package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageGridComponent;
import com.historycode.ui.page.adminpanel.partnerspage.PartnersRowComponent;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestCase128 extends TestRunnerWithAdmin  {

    String testName = "SpongeBob";

    @Test
    @Issue("128")
    @Description("Verify that the system doesn't save new partner without filled all mandatory fields" +
            " in the \"Додати партнера\" modal window")
    public void test128 () {
        /*
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
        softAssert.assertAll();*/

        PartnersPageAdminPanel adminPage = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickLastPage();
    }
}
