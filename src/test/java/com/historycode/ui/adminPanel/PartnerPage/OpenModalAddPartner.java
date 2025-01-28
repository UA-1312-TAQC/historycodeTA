package com.historycode.ui.adminPanel.PartnerPage;

import com.historycode.ui.component.HelpUs.PartnerModalComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenModalAddPartner extends TestRunnerWithAdmin {


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
}
