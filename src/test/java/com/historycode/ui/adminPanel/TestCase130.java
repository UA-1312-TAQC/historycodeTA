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

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestCase130  extends TestRunnerWithAdmin {

    String projectRoot = System.getProperty("user.dir");
    Path SpongeLogo = Paths.get(projectRoot, "src", "test", "java", "com", "historycode", "resources", "logo.jpeg");

    String testName = "SpongeBob";
    String testDescription = "Our guru and cooker";

    @BeforeMethod
    public void setupForTest() {
        login();
    }

    @Test
    @Issue("130")
    @Description("Verify that the admin can add a description to a partner's card")
    public void test130 () throws InterruptedException {
        PartnersPageAdminPanel adminPage = new PartnersPageAdminPanel(driver);

        AdminMenuBarComponent menuBar = adminPage.getAdminMenuBar();
        CreatePartnersModal createModal = menuBar.goToPartnersPage().clickAddNewPartnersButton();

        InputElement name = createModal.name;
        name.setInputField("SpongeBob");

        TextAreaElement description = createModal.description;
        description.setTextArea("Our guru and cooker");

        LogoElement logo = createModal.logo;
        logo.uploadLogo(SpongeLogo.toString());

        createModal.clickSaveButton();
        adminPage.sleep(5000);

        createModal.clickCloseButton();

        String baseUrl = testValueProvider.getBaseUIUrl();
        driver.get(baseUrl + "/partners-page");
        PartnerPage basePage = new PartnerPage(driver);

        basePage.scrollToEndOfPage();
        basePage.sleep(5000);

        basePage.hoverOverNotKeyPartner(testName);

        Assert.assertEquals(basePage.getPopoverDescription(), testDescription);
    }
}

