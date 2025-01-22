package com.historycode.ui.adminPanel.PartnerPage;

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
import org.testng.asserts.SoftAssert;

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

        basePage.scrollToElement(basePage.getConstantKeyPartners());
        basePage.hoverOverKeyPartner(testName);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }
}
