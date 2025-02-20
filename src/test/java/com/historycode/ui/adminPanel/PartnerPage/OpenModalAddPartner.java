package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.component.DropdownBase;
import com.historycode.ui.component.HelpUs.PartnerModalComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OpenModalAddPartner extends BaseTestRunnerWithAdmin {


    @Test
    @Issue("126")
    @Description("Verify that a modal window opens after click 'Додати' button")
    public void openModalWindow() {

        PartnerModalComponent historyPage = new HistoryCodesAdminPanelPage(driver)
                .getHistoryCodesAdminPanelGridComponent()
                .getRowByNum(0)
                .clickEdit()
                .clickAddPartnerButton();

        Assert.assertTrue(historyPage.getPartnerModal().isDisplayed(), "Modal window is not displayed");

    }

    @Test
    @Issue("124")
    @Description("Verify that the admin can add an existing partner")
    public void addExistingPartner() {

        DropdownBase historyPage = new HistoryCodesAdminPanelPage(driver)
                .getHistoryCodesAdminPanelGridComponent()
                .getRowByNum(0)
                .clickEdit()
                .getPartnersDropdown();


        historyPage.selectOption(4,0,0);
        Assert.assertFalse(historyPage.getChosenElements().isEmpty(), "Chosen element is not displayed");

    }
}
