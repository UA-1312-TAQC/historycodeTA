package com.historycode.ui.adminPanel;

import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.LogoElement;
import com.historycode.ui.elements.adminPanel.TextAreaElement;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.page.partnerPage.PartnerPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;

import org.testng.annotations.Test;

public class PartnerPageTestCases extends TestRunnerWithAdmin  {

    String testName = "SpongeBob";
    String testDescription = "Our optimistic and energetic sponge";
    String testLogo = "logo.jpeg";
    String testMenuPageName = "Партнери";

    @Test
    @Issue("130")
    @Description("Verify that the admin can add a description to a partner's card")
    public void test130 () {
                CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        InputElement name = createModal.name;
        name.setInputField(testName);

        TextAreaElement description = createModal.description;
        description.setTextArea(testDescription);

        LogoElement logo = createModal.logo;
        logo.uploadLogo(testLogo);

        createModal.clickSaveButton();
        createModal.clickCloseButton();

        PartnerPage basePage = new PartnerPage(driver);
        basePage.openBurgerMenu().clickMenuItem(testMenuPageName);

        basePage.scrollToEndOfPage();
        basePage.hoverOverNotKeyPartner(testName);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }

    @Test
    @Issue("128")
    @Description("Verify that the system doesn't save new partner without filled all mandatory fields" +
            " in the \"Додати партнера\" modal window")
    public void test128 () {
        CreatePartnersModal createModal = new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();

        InputElement name = createModal.name;
        name.setInputField(testName);

        Assert.assertFalse(createModal.isSaveButtonEnabled());
    }
}
