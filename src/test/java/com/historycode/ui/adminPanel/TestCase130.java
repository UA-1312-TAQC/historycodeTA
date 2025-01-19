package com.historycode.ui.adminPanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestCase130  extends TestRunnerWithAdmin {

    String testName = "SpongeBob";
    String testDescription = "Our optimistic and energetic sponge";
    String testLogo = "logo.jpeg";

    @BeforeMethod
    public void setupForTest() {
        login();
    }

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

        String baseUrl = testValueProvider.getBaseUIUrl();
        driver.get(baseUrl + "/partners-page");
        PartnerPage basePage = new PartnerPage(driver);

        basePage.scrollToEndOfPage();
        basePage.hoverOverNotKeyPartner(testName);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }
}

