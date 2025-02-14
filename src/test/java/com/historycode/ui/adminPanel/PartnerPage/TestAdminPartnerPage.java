package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import com.historycode.ui.utils.ImageLoader;

public class TestAdminPartnerPage extends TestRunnerWithAdmin {

    protected final String testName = "SpongeBob";
    protected final String testDescription = "Our optimistic and energetic sponge";
    protected final String testLogo = "uploadfiles/logo.webp";
    protected final String testLogoNew = "uploadfiles/test.webp";
    protected final String testLogoSrc = ImageLoader.getBase64FromFile(testLogo);
    protected final String testLogoNewSrc = ImageLoader.getBase64FromFile(testLogoNew);

    protected CreatePartnersModal openCreateModal() {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
        PartnersPageAdminPanel partnerPage = new PartnersPageAdminPanel(driver);
        return partnerPage
               .getAdminMenuBar()
               .goToPartnersPage()
               .clickAddNewPartnersButton();
    }
}
