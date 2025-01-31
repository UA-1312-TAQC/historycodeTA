package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.page.adminpanel.partnerspage.PartnersPageAdminPanel;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import com.historycode.ui.utils.ImageLoader;

public class TestAdminPartnerPage extends TestRunnerWithAdmin {

    protected final String testName = "SpongeBob";
    protected final String testDescription = "Our optimistic and energetic sponge";
    protected final String testLogo = "uploadfiles/logo.webp";
    protected final String testLogoSrc = ImageLoader.getBase64FromFile(testLogo);
    ;

    protected CreatePartnersModal openCreateModal() {
        return new PartnersPageAdminPanel(driver)
                .getAdminMenuBar()
                .goToPartnersPage()
                .clickAddNewPartnersButton();
    }
}
